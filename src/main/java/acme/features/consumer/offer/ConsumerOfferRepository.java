
package acme.features.consumer.offer;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.offers.Offer;
import acme.entities.spamlist.Spamlist;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ConsumerOfferRepository extends AbstractRepository {

	@Query("select a from Offer a where a.id = ?1")
	Offer findOneById(int id);

	@Query("select a from Offer a where now()<=a.deadline")
	Collection<Offer> findManyAll();

	@Query("select a from Offer a where a.ticker = ?1")
	Offer findOneByTicker(String ticker);

	@Query("select a from Spamlist a where a.idiom = ?1")
	Spamlist findEN(String idiom);

	@Query("select a from Spamlist a where a.idiom = ?1")
	Spamlist findES(String idiom);

}
