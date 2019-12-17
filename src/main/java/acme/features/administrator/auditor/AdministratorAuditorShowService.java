
package acme.features.administrator.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorAuditorShowService implements AbstractShowService<Administrator, Auditor> {

	@Autowired
	private AdministratorAuditorRepository repository;


	@Override
	public boolean authorise(final Request<Auditor> request) {
		assert request != null;
		Boolean res = request.getPrincipal().hasRole(Administrator.class);

		return res;
	}

	@Override
	public void unbind(final Request<Auditor> request, final Auditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "statement", "enabledRole");

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

}
