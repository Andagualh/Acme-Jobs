
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.descriptor.Descriptor;
import acme.entities.duty.Duty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneJobById(int id);

	@Query("select d from Duty d where d.descriptor.id = ?1")
	Collection<Duty> findManyByDescriptorId(int id);

	@Query("select d from Descriptor d where d.id = ?1")
	Descriptor findOneDescriptorById(int id);

}
