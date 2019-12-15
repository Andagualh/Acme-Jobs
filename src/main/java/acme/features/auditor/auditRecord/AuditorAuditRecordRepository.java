
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecord.AuditRecord;
import acme.entities.job.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select j from AuditRecord j where j.id = ?1")
	AuditRecord findOneAuditRecordById(int id);

	@Query("select j from AuditRecord j where j.job.id = ?1")
	Collection<AuditRecord> findManyByJobId(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select a from Auditor a where a.id = ?1")
	Auditor findOneAuditorById(int id);

	@Query("select a from AuditRecord a where a.auditor.id = ?1")
	Collection<AuditRecord> findManyByAuditRecordsByAuditorId(Integer id);

}
