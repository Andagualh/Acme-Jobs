
package acme.features.authenticated.sponsor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import acme.entities.roles.Sponsor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Service
public interface AuthenticatedSponsorRepository extends AbstractRepository {

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findOneSponsorByUserId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

}
