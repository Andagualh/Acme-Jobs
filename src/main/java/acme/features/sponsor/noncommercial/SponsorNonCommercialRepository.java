
package acme.features.sponsor.noncommercial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.nonCommercial.NonCommercial;
import acme.framework.repositories.AbstractRepository;

public interface SponsorNonCommercialRepository extends AbstractRepository {

	@Query("select j from NonCommercial j where j.id = ?1")
	NonCommercial findOneNonCommercialBannerById(int id);

	@Query("select j from NonCommercial j where j.sponsor.id = ?1")
	Collection<NonCommercial> findManyBySponsorId(int sponsorid);

}
