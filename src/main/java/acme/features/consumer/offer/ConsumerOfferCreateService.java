
package acme.features.consumer.offer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.entities.spamlist.Spamlist;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	@Autowired
	ConsumerOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationmoment");
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "reward", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", false);
		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		assert request != null;
		Offer result;

		result = new Offer();

		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAccepted, isDuplicated;
		Calendar calendar;
		Date minimumDeadLine;
		String ticker = entity.getTicker();

		Spamlist SpEN = this.repository.findEN("EN");
		Spamlist SpES = this.repository.findES("ES");

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 7);
			minimumDeadLine = calendar.getTime();
			if (entity.getDeadline() != null) {
				errors.state(request, entity.getDeadline().after(minimumDeadLine), "deadline", "acme.validation.deadline");
			}
		}

		if (!errors.hasErrors("ticker")) {
			isDuplicated = this.repository.findOneByTicker(ticker) == null;
			errors.state(request, isDuplicated, "ticker", "acme.validation.ticker");
		}

		if (!errors.hasErrors()) {
			errors.state(request, !entity.spam(SpEN) || !entity.spam(SpES), "title", "acme.validation.spamlist");
		}

		isAccepted = request.getModel().getBoolean("accept");
		errors.state(request, isAccepted, "accept", "acme.validation.accept");
	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		entity.setCreationmoment(moment);

		this.repository.save(entity);

	}

}
