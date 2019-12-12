
package acme.features.administrator.nonCommercial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.nonCommercial.NonCommercial;
import acme.entities.spamlist.Spamlist;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNonCommercialRepository extends AbstractRepository {

	@Query("select a from NonCommercial a where a.id = ?1")
	NonCommercial findOneById(int id);

	@Query("select a from NonCommercial a")
	Collection<NonCommercial> findManyAll();

	@Query("select a from Spamlist a where a.idiom = ?1")
	Spamlist findEN(String idiom);

	@Query("select a from Spamlist a where a.idiom = ?1")
	Spamlist findES(String idiom);

}
