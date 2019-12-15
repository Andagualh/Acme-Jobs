
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.message.Message;
import acme.entities.messageThread.MessageThread;
import acme.entities.messageThread.MessageThreadAuthenticated;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select m from MessageThread m join m.users u where u.user.id = ?1")
	Collection<MessageThread> findManyMessageThreadByUserId(int id);

	@Query("select mt from MessageThread mt where mt.id=?1")
	MessageThread findMessageThreadById(int id);

	@Query("select m from Message m where m.thread.id = ?1")
	Collection<Message> findManyMessagesById(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findUserAccountById(int id);

	@Query("select mt from MessageThread mt join mt.message where mt.id = ?1")
	MessageThread findOneById(int id);

	@Query("select mta from MessageThreadAuthenticated mta where mta.user.id = ?1")
	MessageThreadAuthenticated findOneMessageThreadAuthenticatedById(int id);

	@Query("select mta from MessageThreadAuthenticated mta where mta.thread.id = ?1")
	Collection<MessageThreadAuthenticated> findManyMessageThreadAuthenticatedByMTId(int id);

	@Query("select ua from UserAccount ua where ua.username = ?1 AND ua.identity.email = ?2")
	UserAccount findOneUserAccountByUsernameEmail(String username, String email);
}
