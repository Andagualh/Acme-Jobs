
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.application.Application;
import acme.framework.repositories.AbstractRepository;

public interface EmployerApplicationsRepository extends AbstractRepository {

	@Query("select a from Application a where a.job.employer.id = ?1")
	Collection<Application> findManyByEmployerId(int EmployerId);

	@Query("select a from Application a where a.id = ?1")
	Application findOneById(int id);

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.ref asc")
	Collection<Application> findManyByReference(int EmployerId);

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.status asc")
	Collection<Application> findManyByStatus(int EmployerId);

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.creationmoment desc")
	Collection<Application> findManyByCreationMoment(int EmployerId);

}
