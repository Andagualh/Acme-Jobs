
package acme.features.authenticated.employer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import acme.entities.roles.Employer;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Service
public interface AuthenticatedEmployerRepository extends AbstractRepository {

	@Query("select e from Employer e where e.userAccount.id = ?1")
	Employer findOneEmployerByUserId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

}
