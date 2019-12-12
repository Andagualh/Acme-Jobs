
package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptor.Descriptor;
import acme.entities.job.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	@Autowired
	private EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		int principalId = request.getPrincipal().getActiveRoleId();

		int jobId = request.getModel().getInteger("id");
		Job j = this.repository.findOneJobById(jobId);

		boolean result;
		result = j.getEmployer().getId() == principalId;
		return result;
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

		//String description = entity.getDescriptor().getDescription();

		request.unbind(entity, model, "reference", "status", "title", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "description", "finalMode");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		Job result;
		int id = request.getModel().getInteger("id");

		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//		if (request.getModel().getBoolean("finalMode")) {
		//			errors.state(request, !request.getModel().getBoolean("finalMode"), "finalMode", "acme.validation.finalMode");
		//		}
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		Descriptor oldDescriptor = entity.getDescriptor();
		if (oldDescriptor != null) {
			oldDescriptor.setJob(null);
			this.repository.save(oldDescriptor);
		}

		String description = request.getModel().getString("descriptor");
		if (description == "" || description == null) {
			entity.setDescriptor(null);
		} else {

			Descriptor desc = new Descriptor();
			desc.setDescription(description);
			desc.setJob(entity);
			entity.setDescriptor(desc);
			this.repository.save(desc);

		}
		this.repository.save(entity);

	}
}
