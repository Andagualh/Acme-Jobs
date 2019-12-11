
package acme.features.administrator.commercial;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercial.Commercial;
import acme.entities.creditCard.CreditCard;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
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

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("number", "");
		} else {
			request.transfer(model, "status");
		}

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

		//A credit card number must have between 13 and 16 digits. It must start with:
		if (!errors.hasErrors("number")) {
			errors.state(request, number.length() <= 16 && number.length() >= 13, "number", "administrator.commercial.form.error.number", number);
		}

		/*
		 * A credit card number must start with:
		 * 4 for Visa cards
		 * 5 for Master cards
		 * 3 for American Express cards
		 * 6 for Discover cards
		 */
		if (!errors.hasErrors("number")) {
			errors.state(request, number.startsWith("4") || number.startsWith("5") || number.startsWith("3") || number.startsWith("6"), "number", "administrator.commercial.form.error.invalidStart", number);
		}

		/*
		 * Program for credit card number validation:
		 * The problem can be solved by using Luhn algorithm.
		 * Luhn check or the Mod 10 check, which can be described as follows (for illustration,
		 * consider the card number 4388576018402626):
		 *
		 * Step 1. Double every second digit from right to left. If doubling of a digit results in a
		 * two-digit number, add up the two digits to get a single-digit number (like for 12:1+2,
		 * 18=1+8).
		 *
		 * Step 2. Now add all single-digit numbers from Step 1.
		 * 4 + 4 + 8 + 2 + 3 + 1 + 7 + 8 = 37
		 *
		 * Step 3. Add all digits in the odd places from right to left in the card number.
		 * 6 + 6 + 0 + 8 + 0 + 7 + 8 + 3 = 38
		 *
		 * Step 4. Sum the results from Step 2 and Step 3.
		 * 37 + 38 = 75
		 *
		 * Step 5. If the result from Step 4 is divisible by 10, the card number is valid; otherwise, it is
		 * invalid.
		 */
		if (!errors.hasErrors("number")) {
			String reverse = IntStream.rangeClosed(0, number.length() - 1).boxed().map(x -> number.charAt(number.length() - 1 - x)).collect(Collectors.toList()).toString();
			reverse = reverse.replace(",", "");
			reverse = reverse.replace("[", "");
			reverse = reverse.replace("]", "");
			reverse = reverse.replace(" ", "");
			String[] numbers = reverse.split("");

			Integer i = 0;
			Integer sum1 = 0;
			for (String a : numbers) {
				Integer aux = Integer.parseInt(a);
				if (i % 2 == 1) {
					aux = aux * 2;
					String aux2 = aux.toString();
					if (aux2.length() > 1) {
						Integer i1 = Integer.parseInt(aux2.substring(0, 1));
						Integer i2 = Integer.parseInt(aux2.substring(1));
						aux = i1 + i2;
					}
					sum1 = sum1 + aux;
				}
				i++;
			}

			i = 0;
			Integer sum2 = 0;
			for (String a : numbers) {
				Integer aux = Integer.parseInt(a);
				if (i % 2 == 0) {
					sum2 = sum2 + aux;
				}
				i++;
			}

			Integer result = sum1 + sum2;
			errors.state(request, result % 10 == 0, "number", "administrator.commercial.form.error.numberInvalid", number);

		}

		if (!errors.hasErrors("cvv")) {
			errors.state(request, cvv.length() == 3, "cvv", "administrator.commercial.form.error.cvv", cvv);
		}

		if (!errors.hasErrors("deadline")) {
			errors.state(request, Pattern.matches("[0-9]{2}/[0-9]{4}", deadline), "deadline", "administrator.commercial.form.error.deadline", deadline);
		}

		if (!errors.hasErrors("deadline")) {
			String[] monthYear = deadline.split("/");
			Integer month = Integer.parseInt(monthYear[0]);
			errors.state(request, month >= 1 && month <= 12, "deadline", "administrator.commercial.form.error.month", deadline);
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
