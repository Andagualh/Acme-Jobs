
package acme.features.authenticated.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerApplicationsShowService implements AbstractShowService<Worker, Application> {

	@Autowired
	WorkerApplicationsRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		boolean result;
		Application application;
		int appId;
		Worker worker;
		Principal principal;

		appId = request.getModel().getInteger("id");
		application = this.repository.findOneApplicationById(appId);
		worker = application.getWorker();
		principal = request.getPrincipal();
		result = application.isFinalMode() || !application.isFinalMode() && worker.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ref", "creationMoment", "status", "statement", "skill", "qualification");

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneApplicationById(id);
		return result;
	}
}
