
package acme.features.administrator.nonCommercial;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.nonCommercial.NonCommercial;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorNonCommercialListService implements AbstractListService<Administrator, NonCommercial> {

	@Autowired
	AdministratorNonCommercialRepository repository;


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
		assert request != null;
		Collection<NonCommercial> nonCommercialBannerList;

		nonCommercialBannerList = this.repository.findManyAll();

		return nonCommercialBannerList;
	}

}
