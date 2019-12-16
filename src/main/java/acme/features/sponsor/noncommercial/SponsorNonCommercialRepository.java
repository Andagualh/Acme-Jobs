
package acme.features.sponsor.noncommercial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.nonCommercial.NonCommercial;
import acme.entities.roles.Sponsor;
import acme.entities.spamlist.Spamlist;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNonCommercialRepository extends AbstractRepository {

	@Query("select j from NonCommercial j where j.id = ?1")
	NonCommercial findOneNonCommercialBannerById(int id);

	@Query("select j from NonCommercial j where j.sponsor.id = ?1")
	Collection<NonCommercial> findManyBySponsorId(int sponsorid);

	@Query("select s from Sponsor s where s.id = ?1")
	Sponsor findSponsorById(int activeRoleId);

	@Query("select s from Spamlist s where s.idiom = ?1")
	Spamlist findSpamLists(String idiom);
}
