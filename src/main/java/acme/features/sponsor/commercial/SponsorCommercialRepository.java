
package acme.features.sponsor.commercial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.commercial.Commercial;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

public interface SponsorCommercialRepository extends AbstractRepository {

	@Query("select j from Commercial j where j.id = ?1")
	Commercial findOneCommercialBannerById(int id);

	@Query("select j from Commercial j where j.sponsor.id = ?1")
	Collection<Commercial> findManyBySponsorId(int employerid);

	@Query("select s from Sponsor s where s.id = ?1")
	Sponsor findSponsorById(int activeRoleId);

	//	@Query("select cc from CreditCard cc where cc.commercial.id = ?1")
	//	CreditCard findCreditCardByCommercialId(int id);

}
