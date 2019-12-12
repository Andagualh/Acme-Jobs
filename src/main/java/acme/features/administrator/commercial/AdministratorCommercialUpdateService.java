
package acme.features.administrator.commercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercial.Commercial;
import acme.entities.spamlist.Spamlist;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCommercialUpdateService implements AbstractUpdateService<Administrator, Commercial> {

	//Internal State

	@Autowired
	AdministratorCommercialRepository repository;

	//Service Interface


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

		request.unbind(entity, model, "banner", "slogan", "url", "card");

	}

	@Override
	public Commercial findOne(final Request<Commercial> request) {
		assert request != null;

		Commercial result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Commercial> request, final Commercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Spamlist SpEN = this.repository.findEN("EN");
		Spamlist SpES = this.repository.findES("ES");

		if (!errors.hasErrors()) {
			errors.state(request, !entity.spam(SpEN) || !entity.spam(SpES), "slogan", "acme.validation.spamlist");
		}

	}

	@Override
	public void update(final Request<Commercial> request, final Commercial entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
