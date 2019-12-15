
package acme.features.auditor.auditRecord;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecord.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class AuditorAuditRecordListMineService implements AbstractListService<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordRepository repository;


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
		model.setAttribute("reference", entity.getJob().getReference());
	}

	@Override
	public Collection<AuditRecord> findMany(final Request<AuditRecord> request) {
		assert request != null;

		Collection<AuditRecord> result;
		Collection<AuditRecord> auditRecords;

		int auditorId = request.getPrincipal().getActiveRoleId();

		auditRecords = this.repository.findManyByAuditRecordsByAuditorId(auditorId);

		result = auditRecords.stream().filter(x -> x.finalMode()).collect(Collectors.toList());

		return result;
	}

}
