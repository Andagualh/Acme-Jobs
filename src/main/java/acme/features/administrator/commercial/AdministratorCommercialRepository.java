
package acme.features.administrator.commercial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.commercial.Commercial;
import acme.entities.creditCard.CreditCard;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCommercialRepository extends AbstractRepository {

	@Query("select a from Commercial a where a.id = ?1")
	Commercial findOneById(int id);

	@Query("select a from Commercial a")
	Collection<Commercial> findManyAll();

	@Query("select cc from CreditCard cc where cc.commercial.id = ?1")
	CreditCard findCreditCardByCommercialId(int id);

}
