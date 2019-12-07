
package acme.features.administrator.nonCommercial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.nonCommercial.NonCommercial;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNonCommercialRepository extends AbstractRepository {

	@Query("select a from NonCommercial a where a.id = ?1")
	NonCommercial findOneById(int id);

	@Query("select a from NonCommercial a")
	Collection<NonCommercial> findManyAll();

}
