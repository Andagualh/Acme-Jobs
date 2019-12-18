
package acme.features.administrator.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.AcceptedAuditor;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAuditorUpdateService implements AbstractUpdateService<Administrator, Auditor> {

	@Autowired
	private AdministratorAuditorRepository repository;


	@Override
	public boolean authorise(final Request<Auditor> request) {
		assert request != null;
		return true;
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
	public Auditor findOne(final Request<Auditor> request) {
		assert request != null;
		Auditor result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditorById(id);

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

		UserAccount userAccount;
		AcceptedAuditor acceptedAuditor;
		Integer uAId;

		uAId = this.repository.findUAIdByAuditorId(entity.getId());
		userAccount = this.repository.findOneUserAccountById(uAId);
		acceptedAuditor = new AcceptedAuditor();
		userAccount.addRole(acceptedAuditor);
		acceptedAuditor.setUserAccount(userAccount);
		this.repository.save(acceptedAuditor);
		this.repository.save(userAccount);
	}

}
