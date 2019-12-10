
package acme.features.employer.descriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptor.Descriptor;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerDescriptorShowService implements AbstractShowService<Authenticated, Descriptor> {

	@Autowired
	EmployerDescriptorRepository repository;


	@Override
	public boolean authorise(final Request<Descriptor> request) {
		assert request != null;

		boolean result;
		Descriptor descriptor;
		int appId;
		Employer employer;
		Principal principal;

		appId = request.getModel().getInteger("id");
		descriptor = this.repository.findOneDescriptorById(appId);
		employer = descriptor.getJob().getEmployer();
		principal = request.getPrincipal();
		result = descriptor.getJob().isFinalMode() || !descriptor.getJob().isFinalMode() && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Descriptor> request, final Descriptor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description");
	}

	@Override
	public Descriptor findOne(final Request<Descriptor> request) {
		assert request != null;

		Descriptor result;
		int id = request.getModel().getInteger("id");

		result = this.repository.findOneDescriptorById(id);
		return result;
	}

}
