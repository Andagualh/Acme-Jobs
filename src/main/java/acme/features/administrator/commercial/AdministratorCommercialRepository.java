
package acme.features.administrator.commercial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.commercial.Commercial;
import acme.entities.spamlist.Spamlist;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCommercialRepository extends AbstractRepository {

	@Query("select a from Commercial a where a.id = ?1")
	Commercial findOneById(int id);

	@Query("select a from Commercial a")
	Collection<Commercial> findManyAll();

	@Query("select a from Spamlist a where a.idiom = ?1")
	Spamlist findEN(String idiom);

	@Query("select a from Spamlist a where a.idiom = ?1")
	Spamlist findES(String idiom);

}
