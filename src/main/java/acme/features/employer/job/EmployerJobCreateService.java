
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptor.Descriptor;
import acme.entities.job.Job;
import acme.entities.roles.Employer;
import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	private EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "description");

	}

	@Override
	public Job instantiate(final Request<Job> request) {
		Principal principal = request.getPrincipal();
		int employerId = principal.getActiveRoleId();

		Employer e = this.repository.findEmployerById(employerId);
		Job result;
		result = new Job();
		result.setEmployer(e);
		result.setApplication(null);
		result.setStatus("DRAFT");
		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String description = request.getModel().getString("descriptor-description");
		Boolean isSpamEN, isSpamES;
		String reallyBigString;
		reallyBigString = request.getModel().getString("title") + " " + request.getModel().getString("moreInfo") + " " + request.getModel().getString("description") + " " + request.getModel().getString("descriptor-description");
		Spamlist spamEN = this.repository.findSpamLists("EN");
		Spamlist spamES = this.repository.findSpamLists("ES");

		if (!errors.hasErrors("descriptor-description")) {
			errors.state(request, description != "", "descriptor-description", "employer.job.descriptor.blank");
		}
		isSpamEN = this.isSpam(reallyBigString, spamEN, entity);
		isSpamES = this.isSpam(reallyBigString, spamES, entity);

		errors.state(request, !isSpamEN || !isSpamES, "reference", "acme.validation.spam");

	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		String description = request.getModel().getString("descriptor-description");

		Descriptor desc = new Descriptor();
		desc.setDescription(description);
		desc.setJob(entity);
		entity.setDescriptor(desc);
		this.repository.save(desc);

		this.repository.save(entity);
	}

	//Método Auxiliar

	private Boolean isSpam(final String reallyBigString, final Spamlist sl, final Job entity) {

		Collection<Spamword> spamwords = sl.getSpamwordslist();

		Double numSpamWords = 0.;

		for (Spamword sw : spamwords) {
			String spamword = sw.getSpamword();
			numSpamWords = numSpamWords + this.numDeSpamwords(reallyBigString.toLowerCase(), spamword, 0.);
		}

		return numSpamWords / 100 > sl.getThreshold();
	}

	private Double numDeSpamwords(final String fullText, final String spamword, final Double u) {
		if (!fullText.contains(spamword)) {
			return u;
		} else {
			Integer a = fullText.indexOf(spamword);
			return this.numDeSpamwords(fullText.substring(a + 1), spamword, u + 1);
		}
	}

}
