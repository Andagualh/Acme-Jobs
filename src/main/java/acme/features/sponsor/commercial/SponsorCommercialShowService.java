
package acme.features.sponsor.commercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercial.Commercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorCommercialShowService implements AbstractShowService<Sponsor, Commercial> {

	@Autowired
	SponsorCommercialRepository repository;


	@Override
	public boolean authorise(final Request<Commercial> request) {
		assert request != null;

		boolean result;
		Commercial banner;
		int appId;
		Sponsor sponsor;
		Principal principal;

		appId = request.getModel().getInteger("id");
		banner = this.repository.findOneCommercialBannerById(appId);
		sponsor = banner.getSponsor();
		principal = request.getPrincipal();
		result = sponsor.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Commercial> request, final Commercial entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "banner", "slogan", "url", "ownerName", "expireDate", "ccv", "card");

	}

	@Override
	public Commercial findOne(final Request<Commercial> request) {
		assert request != null;

		Commercial result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneCommercialBannerById(id);

		return result;
	}

}
