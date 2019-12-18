
package acme.features.employer.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationsUpdateService implements AbstractUpdateService<Employer, Application> {

	//Repository
	@Autowired
	EmployerApplicationsRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "justification");
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

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isRejected;

		isRejected = request.getModel().getAttribute("status").equals("REJECTED") && request.getModel().getAttribute("justification").toString().isEmpty();

		if (!errors.hasErrors("status")) {
			errors.state(request, !isRejected, "justification", "acme.validation.justification");
		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date updatedStatusMoment;

		Boolean change = request.getModel().getString("oldstatus") != request.getModel().getString("status");

		if (change) {
			updatedStatusMoment = new Date(System.currentTimeMillis() - 1);
			entity.setUpdatedStatusMoment(updatedStatusMoment);
		}

		this.repository.save(entity);

	}

}
