
package acme.features.administrator.auditor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorAuditorListService implements AbstractListService<Administrator, Auditor> {

	@Autowired
	private AdministratorAuditorRepository repository;


	@Override
	public boolean authorise(final Request<Auditor> request) {
		assert request != null;

		int ppalUserId = request.getPrincipal().getAccountId();
		UserAccount u = this.repository.findOneUserAccountById(ppalUserId);

		return u.hasRole(Administrator.class);
	}

	@Override
	public void unbind(final Request<Auditor> request, final Auditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "statement");
		model.setAttribute("uAId", entity.getUserAccount().getId());
	}

	@Override
	public Collection<Auditor> findMany(final Request<Auditor> request) {

		return this.repository.findPendingAuditors();

	}

}
