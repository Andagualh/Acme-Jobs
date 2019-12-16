
package acme.features.sponsor.commercial;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercial.Commercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class SponsorCommercialListService implements AbstractListService<Sponsor, Commercial> {

	@Autowired
	SponsorCommercialRepository repository;


	@Override
	public boolean authorise(final Request<Commercial> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Commercial> request, final Commercial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "banner", "slogan");
	}

	@Override
	public Collection<Commercial> findMany(final Request<Commercial> request) {
		assert request != null;

		Collection<Commercial> res;
		Principal principal;
		principal = request.getPrincipal();

		res = this.repository.findManyBySponsorId(principal.getActiveRoleId());

		return res;
	}

}
