
package acme.features.authenticated.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.entities.duty.Duty;
import acme.entities.job.Job;
import acme.entities.roles.Worker;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where now()<=j.deadline")
	Collection<Job> findMany();

	@Query("select d from Duty d where d.descriptor.id = ?1")
	Collection<Duty> findManyDutiesById(int id);

	@Query("select j from Job j where now()<=j.deadline and j.status='PUBLISHED'")
	Collection<Job> findManyPublished();

	@Query("select a from Application a where a.worker.id = ?1 and a.job.id=?2")
	Application findAppByWorkerId(int id, int jobId);

	@Query("select w from Worker w where w.userAccount.id = ?1")
	Worker findWorkerByUserId(int id);
}
