
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
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorCreateService implements AbstractCreateService<Authenticated, Auditor> {

	@Autowired
	private AuthenticatedAuditorRepository repository;


	@Override
	public boolean authorise(final Request<Auditor> request) {
		Principal principal = request.getPrincipal();
		int userId = principal.getAccountId();

		UserAccount ua = this.repository.findOneUserAccountById(userId);
		Auditor c = this.repository.findOneAuditorByUserId(ua.getId());
		//int AuditorId = e.getId();

		return c == null;
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

		request.unbind(entity, model, "firm", "statement");
	}

	@Override
	public Auditor instantiate(final Request<Auditor> request) {

		Auditor result;

		Principal principal;
		UserAccount user;
		int userId;

		principal = request.getPrincipal();
		userId = principal.getAccountId();
		user = this.repository.findOneUserAccountById(userId);

		result = new Auditor();
		result.setUserAccount(user);

		return result;
	}

	@Override
	public void validate(final Request<Auditor> request, final Auditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Auditor> request, final Auditor entity) {
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
