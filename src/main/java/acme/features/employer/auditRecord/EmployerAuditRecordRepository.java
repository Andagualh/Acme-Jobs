
package acme.features.employer.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecord.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerAuditRecordRepository extends AbstractRepository {

	@Query("select j from AuditRecord j where j.id = ?1")
	AuditRecord findOneJobById(int id);

	@Query("select j from AuditRecord j where j.job.id = ?1")
	Collection<AuditRecord> findManyByJobId(int id);

}
