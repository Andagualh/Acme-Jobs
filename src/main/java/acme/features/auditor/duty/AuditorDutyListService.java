
package acme.features.auditor.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duty.Duty;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class AuditorDutyListService implements AbstractListService<Auditor, Duty> {

	@Autowired
	AuditorDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		Boolean res;
		Integer auId = request.getPrincipal().getAccountId();
		Auditor au = this.repository.findOneAuditorById(auId);

		res = request.getPrincipal().hasRole(Auditor.class) && au.getEnabledRole();

		return res;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description");
		model.setAttribute("percent", entity.getPercent());
		model.setAttribute("reference", request.getServletRequest().getParameter("ref"));
	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;

		Collection<Duty> result;

		String descriptorId = request.getServletRequest().getParameter("id");

		result = this.repository.findManyByJobId(Integer.parseInt(descriptorId));

		return result;
	}

}
