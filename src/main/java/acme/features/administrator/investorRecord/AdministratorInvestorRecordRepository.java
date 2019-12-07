
package acme.features.administrator.investorRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investorRecord.InvestorRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorInvestorRecordRepository extends AbstractRepository {

	@Query("select e from InvestorRecord e where e.id = ?1")
	InvestorRecord findOneById(int id);

	@Query("select e from InvestorRecord e")
	Collection<InvestorRecord> findManyAll();

}
