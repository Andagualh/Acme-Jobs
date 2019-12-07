
package acme.features.provider.requests;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.Request;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ProviderRequestsRepository extends AbstractRepository {

	@Query("select r from Request r where r.id = ?1")
	Request findOneRequestById(int id);

	@Query("select r from Request r")
	Collection<Request> findManyRequests();

	@Query("select r from Request r where r.ticker = ?1")
	Request findOneByTicker(String ticker);
}
