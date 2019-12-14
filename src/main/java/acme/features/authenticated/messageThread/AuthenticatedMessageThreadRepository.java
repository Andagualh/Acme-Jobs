
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.message.Message;
import acme.entities.messageThread.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select m from MessageThread m join m.users u where u.id = ?1")
	Collection<MessageThread> findManyMessageThreadByUserId(int id);

	@Query("select mt from MessageThread mt where mt.id=?1")
	MessageThread findMessageThreadById(int id);

	@Query("select m from Message m where m.thread.id = ?1")
	Collection<Message> findManyMessagesById(int id);

	@Query("select u from Authenticated u where u.id = ?1")
	Authenticated findUserAccountById(int id);
}
