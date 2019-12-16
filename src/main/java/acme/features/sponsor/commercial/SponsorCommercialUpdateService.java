
package acme.features.sponsor.commercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercial.Commercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorCommercialUpdateService implements AbstractUpdateService<Sponsor, Commercial> {

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

		request.unbind(entity, model, "banner", "slogan", "url");
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

	@Override
	public void validate(final Request<Commercial> request, final Commercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Commercial> request, final Commercial entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
