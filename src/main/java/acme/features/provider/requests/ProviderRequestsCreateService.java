
package acme.features.provider.requests;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.Request;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderRequestsCreateService implements AbstractCreateService<Provider, Request> {

	@Autowired
	ProviderRequestsRepository repository;


	@Override
	public boolean authorise(final acme.framework.components.Request<Request> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final acme.framework.components.Request<Request> request, final Request entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", false);
		} else {
			request.transfer(model, "accept");
		}

		request.unbind(entity, model, "title", "text", "deadLine", "reward", "ticker");
	}

	@Override
	public Request instantiate(final acme.framework.components.Request<Request> request) {
		assert request != null;
		Request result;

		result = new Request();

		return result;
	}

	@Override
	public void validate(final acme.framework.components.Request<Request> request, final Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadLine;
		String ticker = entity.getTicker();
		boolean isAccepted, isDuplicated;

		if (!errors.hasErrors("deadLine")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 7);
			minimumDeadLine = calendar.getTime();
			if (entity.getDeadLine() != null) {
				errors.state(request, entity.getDeadLine().after(minimumDeadLine), "deadLine", "acme.validation.deadline");
			}
		}

		if (!errors.hasErrors("ticker")) {
			isDuplicated = this.repository.findOneByTicker(ticker) == null;
			errors.state(request, isDuplicated, "ticker", "acme.validation.ticker");
		}

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "acme.validation.accept");
	}

	@Override
	public void create(final acme.framework.components.Request<Request> request, final Request entity) {
		Date creationMoment;

		creationMoment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(creationMoment);
		this.repository.save(entity);
	}
}
