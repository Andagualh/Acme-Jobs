
package acme.features.employer.descriptor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.descriptor.Descriptor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDescriptorRepository extends AbstractRepository {

	@Query("select d from Descriptor d where d.id = ?1")
	Descriptor findOneDescriptorById(int id);

	@Query("select d from Descriptor d where d.id = ?1")
	Collection<Descriptor> findOneeDescriptorById(int id);

}