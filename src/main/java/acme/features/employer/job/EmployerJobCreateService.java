
package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptor.Descriptor;
import acme.entities.job.Job;
import acme.entities.roles.Employer;
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

		request.bind(entity, errors, "descriptor");
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "title", "deadline");
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
		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Job> request, final Job entity) {

		String description = request.getModel().getString("descriptor");
		if (description == "" || description == null) {
			entity.setDescriptor(null);
		} else {
			Descriptor desc = new Descriptor();
			desc.setDescription(description);
			desc.setJob(entity);
			entity.setFinalMode(false);
			entity.setDescriptor(desc);
			this.repository.save(desc);

		}
		this.repository.save(entity);

	}

}
