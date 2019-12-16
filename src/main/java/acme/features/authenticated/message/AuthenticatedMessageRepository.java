
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.message.Message;
import acme.entities.messageThread.MessageThread;
import acme.entities.spamlist.Spamlist;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.thread.id = ?1")
	Collection<Message> findManyMessagesByThreadId(int id);

	@Query("select m from Message m where m.id = ?1")
	Message findOneMessageById(int id);

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneMessageThreadById(int id);

	@Query("select a from Spamlist a where a.idiom = ?1")
	Spamlist findIdiom(String idiom);

}
