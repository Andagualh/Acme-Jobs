
package acme.features.authenticated.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecord.AuditRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuditRecordListService implements AbstractListService<Authenticated, AuditRecord> {

	@Autowired
	AuthenticatedAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "status", "body");
		model.setAttribute("reference", request.getServletRequest().getParameter("ref"));
	}

	@Override
	public Collection<AuditRecord> findMany(final Request<AuditRecord> request) {
		assert request != null;

		Collection<AuditRecord> result;

		String jobId = request.getServletRequest().getParameter("id");

		result = this.repository.findManyPublishedByJobId(Integer.parseInt(jobId));

		return result;
	}

}
