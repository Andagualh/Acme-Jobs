
package acme.features.administrator.commercial;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercial.Commercial;
import acme.entities.creditCard.CreditCard;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCommercialCreateService implements AbstractCreateService<Administrator, Commercial> {

	@Autowired
	AdministratorCommercialRepository repository;


	@Override
	public boolean authorise(final Request<Commercial> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Commercial> request, final Commercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Commercial> request, final Commercial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "banner", "slogan", "url");
	}

	@Override
	public Commercial instantiate(final Request<Commercial> request) {
		Commercial result;

		result = new Commercial();

		return result;
	}

	@Override
	public void validate(final Request<Commercial> request, final Commercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String number = request.getModel().getString("number");
		String deadline = request.getModel().getString("deadline");
		String cvv = request.getModel().getString("cvv");

		if (!errors.hasErrors("number")) {
			errors.state(request, number.length() == 16, "number", "administrator.commercial.form.error.number", number);
		}

		if (!errors.hasErrors("cvv")) {
			errors.state(request, cvv.length() == 3, "cvv", "administrator.commercial.form.error.cvv", cvv);
		}

		if (!errors.hasErrors("deadline")) {
			errors.state(request, Pattern.matches("[0-9]{2}/[0-9]{4}", deadline), "deadline", "administrator.commercial.form.error.deadline", deadline);
		}

	}

	@Override
	public void create(final Request<Commercial> request, final Commercial entity) {
		String ownerName = request.getModel().getString("ownerName");
		String number = request.getModel().getString("number");
		String deadline = request.getModel().getString("deadline");
		String cvv = request.getModel().getString("cvv");

		CreditCard creditCard = new CreditCard();
		creditCard.setOwnerName(ownerName);
		creditCard.setNumber(number);
		creditCard.setDeadline(deadline);
		creditCard.setCvv(cvv);

		this.repository.save(creditCard);

		creditCard.setCommercial(entity);

		this.repository.save(entity);
	}

}
