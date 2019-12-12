
package acme.features.administrator.nonCommercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.nonCommercial.NonCommercial;
import acme.entities.spamlist.Spamlist;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNonCommercialCreateService implements AbstractCreateService<Administrator, NonCommercial> {

	@Autowired
	AdministratorNonCommercialRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercial> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<NonCommercial> request, final NonCommercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<NonCommercial> request, final NonCommercial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "banner", "slogan", "url", "jingle");
	}

	@Override
	public NonCommercial instantiate(final Request<NonCommercial> request) {
		NonCommercial result;

		result = new NonCommercial();

		return result;
	}

	@Override
	public void validate(final Request<NonCommercial> request, final NonCommercial entity, final Errors errors) {
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
	public void create(final Request<NonCommercial> request, final NonCommercial entity) {
		this.repository.save(entity);
	}

}
