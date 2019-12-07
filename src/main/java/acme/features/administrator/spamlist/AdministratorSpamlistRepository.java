
package acme.features.administrator.spamlist;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamlistRepository extends AbstractRepository {

	@Query("select a from Spamlist a")
	Collection<Spamlist> findManyAll();

	@Query("select a from Spamlist a where a.id = ?1")
	Spamlist findOneById(int id);

	@Query("select a from Spamword a where a.spamlist.id = ?1")
	Collection<Spamword> findManySpamwordsById(int id);

	//	This query search a specific Spamword from a Spamlist by the Spamword text and the Spamlist id.
	@Query("select a from Spamword a where a.spamword = ?1 AND a.spamlist.id = ?2")
	Spamword findOneSpamword(String spamword, int id);

	//	This query removes the Spamword with the id of the spamword.
	@Modifying
	@Query("delete from Spamword where id = ?1")
	void deleteSpamword(int id);

}
