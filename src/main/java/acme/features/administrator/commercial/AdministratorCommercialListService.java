
package acme.features.administrator.commercial;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercial.Commercial;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorCommercialListService implements AbstractListService<Administrator, Commercial> {

	@Autowired
	AdministratorCommercialRepository repository;


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

		request.unbind(entity, model, "banner", "slogan", "url", "card");
	}

	@Override
	public Collection<Commercial> findMany(final Request<Commercial> request) {
		assert request != null;
		Collection<Commercial> nonCommercialBannerList;

		nonCommercialBannerList = this.repository.findManyAll();

		return nonCommercialBannerList;
	}

}
