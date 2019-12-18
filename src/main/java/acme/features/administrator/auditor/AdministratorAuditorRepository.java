
package acme.features.administrator.auditor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import acme.entities.roles.AcceptedAuditor;
import acme.entities.roles.Auditor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Service
public interface AdministratorAuditorRepository extends AbstractRepository {

	@Query("select a from Auditor a where a.userAccount.id = ?1")
	Auditor findOneAuditorByUserId(int id);

	@Query("select u from UserAccount u where u.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select a.userAccount.id from Auditor a where a.id = ?1")
	Integer findUAIdByAuditorId(int id);

	@Query("select a from Auditor a where a.id = ?1")
	Auditor findOneAuditorById(int id);

	@Query("SELECT a FROM Auditor a WHERE NOT EXISTS (SELECT aA from AcceptedAuditor aA WHERE aA.userAccount.id = a.userAccount.id)")
	Collection<Auditor> findPendingAuditors();

	@Query("select aA from AcceptedAuditor aA where aA.userAccount.id = ?1")
	AcceptedAuditor findaAByUserId(int id);

}
