
package acme.features.authenticated.announcements;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcements.Announcements;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAnnouncementRepository extends AbstractRepository {

	@Query("select a from Announcements a where a.id = ?1")
	Announcements findOneById(int id);

	@Query("select a from Announcements a where a.creationdate > ?1")
	Collection<Announcements> findManyAll(Date mes);

}
