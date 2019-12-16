
package acme.features.auditor.auditRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecord.AuditRecord;
import acme.entities.job.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "body");

		model.setAttribute("id", request.getServletRequest().getParameter("id"));
	}

	@Override
	public AuditRecord instantiate(final Request<AuditRecord> request) {
		AuditRecord result = new AuditRecord();
		Auditor auditor;
		int auditorid;

		auditorid = request.getPrincipal().getActiveRoleId();
		auditor = this.repository.findOneAuditorById(auditorid);
		result.setAuditor(auditor);

		Job job;
		String jobid = request.getServletRequest().getParameter("id");
		job = this.repository.findOneJobById(Integer.parseInt(jobid));
		result.setJob(job);

		result.setStatus("PUBLISHED");

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<AuditRecord> request, final AuditRecord entity) {

		Job job;
		String jobid = request.getServletRequest().getParameter("id");
		job = this.repository.findOneJobById(Integer.parseInt(jobid));
		entity.setJob(job);

		this.repository.save(entity);
	}

}
