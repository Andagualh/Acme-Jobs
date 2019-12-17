
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duty.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerDutyShowService implements AbstractShowService<Employer, Duty> {

	@Autowired
	EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		boolean result;
		Duty duty;
		int appId;
		Employer employer;
		Principal principal;

		appId = request.getModel().getInteger("id");
		duty = this.repository.findOneDutyById(appId);
		employer = duty.getDescriptor().getJob().getEmployer();
		principal = request.getPrincipal();
		result = duty.getDescriptor().getJob().finalMode() || !duty.getDescriptor().getJob().finalMode() && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "percent");
	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneDutyById(id);

		return result;
	}

}
