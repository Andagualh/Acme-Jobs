
package acme.features.employer.job;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
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
	Collection<Duty> findManyDutiesById(int id);

	@Query("select a from Application a where a.job.id = ?1")
	Collection<Application> findManyApplicationsByJobId(int id);

	@Transactional
	@Modifying
	@Query("delete from Duty d where d.descriptor.id = ?1")
	void deleteDuties(int id);
	@Transactional
	@Modifying
	@Query("delete from Descriptor d where id = ?1")
	void deleteDescriptor(int id);
	@Transactional
	@Modifying
	@Query("delete from AuditRecord a where a.job.id = ?1")
	void deleteAudit(int id);
	@Transactional
	@Modifying
	@Query("delete from Application a where a.job.id = ?1")
	void deleteAppli(int id);

}
