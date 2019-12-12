
package acme.features.employer.descriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptor.Descriptor;
import acme.entities.job.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDescriptorCreateService implements AbstractCreateService<Employer, Descriptor> {

	@Autowired
	private EmployerDescriptorRepository repository;


	@Override
	public boolean authorise(final Request<Descriptor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Descriptor> request, final Descriptor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Descriptor> request, final Descriptor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description");

	}

	@Override
	public Descriptor instantiate(final Request<Descriptor> request) {
		Descriptor result;
		result = new Descriptor();

		return result;
	}

	@Override
	public void validate(final Request<Descriptor> request, final Descriptor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Descriptor> request, final Descriptor entity) {
		String id = request.getServletRequest().getParameter("id");
		Job j = this.repository.findJobById(Integer.parseInt(id));

		entity.setJob(j);
		this.repository.save(entity);

	}

}
