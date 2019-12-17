
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptor.Descriptor;
import acme.entities.duty.Duty;
import acme.entities.job.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	@Autowired
	private EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		boolean res = false;
		assert request != null;

		Descriptor descriptor;
		int principalId = request.getPrincipal().getActiveRoleId();

		String descriptorid = request.getServletRequest().getParameter("id");

		descriptor = this.repository.findOneDescriptorById(Integer.parseInt(descriptorid));
		if (descriptor.getJob().getEmployer().getId() == principalId) {
			res = true;
		}

		return res;

	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "percent");

		model.setAttribute("id", request.getServletRequest().getParameter("id"));
	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		Duty result;
		Descriptor descriptor;

		String descriptorId = request.getServletRequest().getParameter("id");

		descriptor = this.repository.findOneDescriptorById(Integer.parseInt(descriptorId));

		result = new Duty();

		result.setDescriptor(descriptor);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String descriptorId = request.getServletRequest().getParameter("id");
		Integer percent = request.getModel().getInteger("percent");

		Collection<Duty> duties = this.repository.findManyByDescriptorId(Integer.parseInt(descriptorId));

		Integer allPercent = 0;

		if (duties.size() != 0) {
			allPercent = entity.getPercent();

			for (Duty d : duties) {
				allPercent = allPercent + d.getPercent();
			}
		}

		if (!errors.hasErrors("percent")) {
			errors.state(request, percent <= 100 && percent >= 1, "percent", "acme.validation.percent");
		}

		if (!errors.hasErrors()) {
			errors.state(request, allPercent <= 100, "percent", "acme.validation.percent");
		}

	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {

		Descriptor descriptor;

		String descriptorId = request.getServletRequest().getParameter("id");

		descriptor = this.repository.findOneDescriptorById(Integer.parseInt(descriptorId));

		entity.setDescriptor(descriptor);

		this.repository.save(entity);
	}

}
