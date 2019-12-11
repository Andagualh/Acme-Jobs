
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.entities.auditRecord.AuditRecord;
import acme.entities.descriptor.Descriptor;
import acme.entities.duty.Duty;
import acme.entities.job.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findManyByEmployerId(int employerid);

	@Query("select d from Duty d where d.descriptor.id = ?1")
	Collection<Duty> findManyDutiesByDescriptorId(int id);

	@Query("select a from Application a where a.job.id = ?1")
	Collection<Application> findManyApplicationsByJobId(int id);

	@Query("select d from Descriptor d where d.job.id = ?1")
	Descriptor findDescriptorByJobId(int id);

	@Query("select ar from AuditRecord ar where ar.job.id = ?1")
	Collection<AuditRecord> findAuditRecordsByJobId(int id);

	@Query("select du from Duty du where du.id = ?1")
	Duty findOneDutyById(int id);
}
