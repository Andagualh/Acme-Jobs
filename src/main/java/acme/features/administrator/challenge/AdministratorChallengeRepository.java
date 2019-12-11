
package acme.features.administrator.challenge;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenge.Challenge;
import acme.entities.spamlist.Spamlist;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorChallengeRepository extends AbstractRepository {

	@Query("select a from Challenge a where a.id = ?1")
	Challenge findOneById(int id);

	@Query("select a from Challenge a where now()<=a.deadline")
	Collection<Challenge> findManyAll();

	@Query("select a from Spamlist a where a.idiom = ?1")
	Spamlist findEN(String idiom);

	@Query("select a from Spamlist a where a.idiom = ?1")
	Spamlist findES(String idiom);

}
