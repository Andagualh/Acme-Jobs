
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerApplicationsShowService implements AbstractShowService<Employer, Application> {

	// Internal state ---------------------------------------------------------

	@Autowired
	EmployerApplicationsRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		boolean result;
		Application application;
		int appId;
		Employer employer;
		Principal principal;

		appId = request.getModel().getInteger("id");
		application = this.repository.findOneById(appId);
		employer = application.getJob().getEmployer();
		principal = request.getPrincipal();
		result = application.isFinalMode() || !application.isFinalMode() && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ref", "creationMoment", "status", "statement", "skill", "qualification", "justification");
		model.setAttribute("oldstatus", entity.getStatus());
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
