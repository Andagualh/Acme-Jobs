
package acme.features.authenticated.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedAuditorUpdateService implements AbstractUpdateService<Authenticated, Auditor> {

	@Autowired
	private AuthenticatedAuditorRepository repository;


	@Override
	public boolean authorise(final Request<Auditor> request) {
		assert request != null;
		Boolean res;
		Integer auId = request.getPrincipal().getAccountId();
		Auditor au = this.repository.findOneAuditorById(auId);

		res = request.getPrincipal().hasRole(Auditor.class) && au.getEnabledRole();

		return res;
	}

	@Override
	public void bind(final Request<Auditor> request, final Auditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Auditor> request, final Auditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "statement", "enabledRole");
		request.getModel().setAttribute("id", entity.getId());
	}

	@Override
	public Auditor findOne(final Request<Auditor> request) {
		assert request != null;

		Auditor result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneAuditorByUserId(userAccountId);

		return result;
	}

	@Override
	public void validate(final Request<Auditor> request, final Auditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Auditor> request, final Auditor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Auditor> request, final Response<Auditor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
