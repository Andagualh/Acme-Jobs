
package acme.features.sponsor.commercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercial.Commercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCommercialCreateService implements AbstractCreateService<Sponsor, Commercial> {

	@Autowired
	SponsorCommercialRepository repository;


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
		Integer id;
		Sponsor sponsor;

		result = new Commercial();
		id = request.getPrincipal().getActiveRoleId();
		sponsor = this.repository.findSponsorById(id);

		result.setSponsor(sponsor);
		result.setCard(sponsor.getCard());

		return result;
	}

	@Override
	public void validate(final Request<Commercial> request, final Commercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean creditCard = this.repository.findSponsorById(request.getPrincipal().getActiveRoleId()).getCard() == null;

		errors.state(request, !creditCard, "banner", "acme.validation.card");
	}

	@Override
	public void create(final Request<Commercial> request, final Commercial entity) {
		assert request != null;

		this.repository.save(entity);
	}

}
