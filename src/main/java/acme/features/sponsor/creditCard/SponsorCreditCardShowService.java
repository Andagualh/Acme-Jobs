
package acme.features.sponsor.creditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorCreditCardShowService implements AbstractShowService<Sponsor, CreditCard> {

	@Autowired
	SponsorCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		Boolean res = false;
		CreditCard card;
		Integer cardId;
		Integer principalId;

		principalId = request.getPrincipal().getActiveRoleId();
		cardId = request.getModel().getInteger("id");
		card = this.repository.findOneCardById(cardId);

		if (card.getSponsor().getId() == principalId) {
			res = true;
		}

		return res;
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ownerName", "number", "number", "deadline", "cvv");
	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;
		String id;

		id = request.getServletRequest().getParameter("id");
		result = this.repository.findOneCardById(Integer.parseInt(id));

		return result;
	}

}
