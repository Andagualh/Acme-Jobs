
package acme.features.sponsor.noncommercial;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.nonCommercial.NonCommercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class SponsorNonCommercialListService implements AbstractListService<Sponsor, NonCommercial> {

	@Autowired
	SponsorNonCommercialRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercial> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<NonCommercial> request, final NonCommercial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "banner", "slogan", "url", "jingle");

	}

	@Override
	public Collection<NonCommercial> findMany(final Request<NonCommercial> request) {

		Collection<NonCommercial> res;
		Principal principal;

		principal = request.getPrincipal();
		res = this.repository.findManyBySponsorId(principal.getActiveRoleId());

		return res;
	}

}
