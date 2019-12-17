
package acme.features.authenticated.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorCreateService implements AbstractCreateService<Authenticated, Auditor> {

	@Autowired

	AuthenticatedAuditorRepository repository;


	@Override
	public boolean authorise(final Request<Auditor> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int userId = principal.getAccountId();

		UserAccount ua = this.repository.findOneUserAccountById(userId);

		Auditor e = this.repository.findOneAuditorByUserId(ua.getId());

		return e == null;

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
		model.setAttribute("enabledRole", false);

	}

	@Override
	public Auditor instantiate(final Request<Auditor> request) {

		assert request != null;

		Auditor result;
		Principal principal;
		UserAccount userAccount;
		Integer userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Auditor();
		result.setUserAccount(userAccount);
		result.setEnabledRole(false);

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

		if (!request.getPrincipal().hasRole(Administrator.class)) {
			entity.setEnabledRole(false);
		} else if (request.getPrincipal().hasRole(Administrator.class)) {
			entity.setEnabledRole(true);
		}

		this.repository.save(entity);

	}

}
