
package acme.features.administrator.creditCard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCard.CreditCard;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCreditCardRepository extends AbstractRepository {

	@Query("select d from CreditCard d where d.id = ?1")
	CreditCard findOneCardById(int id);

	@Query("select d from CreditCard d where d.number = ?1 AND d.cvv = ?2")
	CreditCard findByNumberCvv(String number, String cvv);

}
