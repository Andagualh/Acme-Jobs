
package acme.features.administrator.dashboards;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Administrator;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select a from Administrator a where a.id = ?1")
	Administrator findOneAdministratorById(int id);

	@Query("select count(a) from Announcements a")
	Integer getTotalAnnouncements();

	@Query("select count(i) from InvestorRecord i")
	Integer getTotalInvestorRecords();

	@Query("select count(c) from CompanyRecords c")
	Integer getTotalCompanyRecords();

	@Query("select min(reward.amount) from Request a where now()<=a.deadLine")
	Double getMinimumRequest();

	@Query("select max(reward.amount) from Request a where now()<=a.deadLine")
	Double getMaximumRequest();

	@Query("select avg(reward.amount) from Request a where now()<=a.deadLine")
	Double getAverageRequest();

	@Query("select stddev(reward.amount) from Request a where now()<=a.deadLine")
	Double getDesviationRequest();

	@Query("select min(reward.amount) from Offer a where now()<=a.deadline")
	Double getMinimumOffers();

	@Query("select max(reward.amount) from Offer a where now()<=a.deadline")
	Double getMaximumOffers();

	@Query("select avg(reward.amount) from Offer a where now()<=a.deadline")
	Double getAverageOffers();

	@Query("select stddev(reward.amount) from Offer a where now()<=a.deadline")
	Double getDesviationOffers();

	@Query("select p.sector, count(p) from CompanyRecords p group by p.sector")
	Collection<Object[]> findAllCompanies();

	@Query("select p.sector, count(p) from InvestorRecord p group by p.sector")
	Collection<Object[]> findAllInvestors();

	@Query("select count(p) from Job p where p.status = 'DRAFT'")
	Integer ratioOfDraftJobs();

	@Query("select count(p) from Job p where p.status = 'PUBLISHED'")
	Integer ratioOfPublishedJobs();

	@Query("select count(p) from Application p where p.status = 'PENDING'")
	Integer ratioOfPendingApplications();

	@Query("select count(p) from Application p where p.status = 'ACCEPTED'")
	Integer ratioOfAcceptedApplications();

	@Query("select count(p) from Application p where p.status = 'REJECTED'")
	Integer ratioOfRejectedApplications();

	@Query("select count(p) from Application p where p.status = 'PENDING' AND datediff(now(),p.updatedStatusMoment) <= 28")
	Integer ratioOfPendingApplicationsInLast4Weeks();

	@Query("select count(p) from Application p where p.status = 'ACCEPTED' AND datediff(now(),p.updatedStatusMoment) <= 28")
	Integer ratioOfAcceptedApplicationsInLast4Weeks();

	@Query("select count(p) from Application p where p.status = 'REJECTED' AND datediff(now(),p.updatedStatusMoment) <= 28")
	Integer ratioOfRejectedApplicationsInLast4Weeks();

	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Double averageNumberJobsPerEmployer();

	@Query("select avg(select count(a) from Application a where a.worker.id = w.id) from Worker w")
	Double averageNumberOfApplicationPerWorker();

	@Query("select avg(select count(a) from Application a where " + "exists(select j from Job j where j.employer.id = e.id and a.job.id = j.id)) " + "from Employer e")
	Double averageNumberOfApplicationPerEmployer();

	@Query("select a.updatedStatusMoment, count(a) from Application a where a.status = 'REJECTED' and a.updatedStatusMoment >= ?1 group by a.updatedStatusMoment")
	List<String[]> numberOfRejectedApplications(Date date);

	@Query("select a.updatedStatusMoment, count(a) from Application a where a.status = 'ACCEPTED' and a.updatedStatusMoment >= ?1 group by a.updatedStatusMoment")
	List<String[]> numberOfAcceptedApplications(Date date);

	@Query("select a.updatedStatusMoment, count(a) from Application a where a.status = 'PENDING' and a.updatedStatusMoment >= ?1 group by a.updatedStatusMoment")
	List<String[]> numberOfPendingApplications(Date date);

}
