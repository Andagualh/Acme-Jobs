
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.auditRecord.AuditRecord;
import acme.entities.duty.Duty;
import acme.entities.job.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "title", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "description", "finalMode");

		model.setAttribute("descriptor", entity.getDescriptor().getDescription());
		model.setAttribute("descriptorId", entity.getDescriptor().getId());
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result = null;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		int jobId = request.getModel().getInteger("id");
		int descriptorId = this.repository.findDescriptorByJobId(jobId).getId();

		Collection<Application> linkedApplications = this.repository.findManyApplicationsByJobId(jobId);
		while (linkedApplications.size() == 0) {

			Collection<AuditRecord> linkedAudits = this.repository.findAuditRecordsByJobId(jobId);
			for (AuditRecord au : linkedAudits) {
				this.repository.delete(au);
			}
			Collection<Duty> linkedDuties = this.repository.findManyDutiesByDescriptorId(descriptorId);
			for (Duty duty : linkedDuties) {
				this.repository.delete(duty);
			}
			this.repository.delete(entity.getDescriptor());
			this.repository.delete(entity);

		}
	}

}
