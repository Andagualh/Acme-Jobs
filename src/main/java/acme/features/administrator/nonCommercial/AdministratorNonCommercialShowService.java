
package acme.features.administrator.nonCommercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.nonCommercial.NonCommercial;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorNonCommercialShowService implements AbstractShowService<Administrator, NonCommercial> {

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
	public NonCommercial findOne(final Request<NonCommercial> request) {
		assert request != null;

		NonCommercial result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
