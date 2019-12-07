
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

}
