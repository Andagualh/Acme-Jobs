
package acme.features.administrator.dashboards;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.dashboard.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		Integer id = request.getPrincipal().getActiveRoleId();

		Administrator a = this.repository.findOneAdministratorById(id);

		return a != null;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer totalAnnouncements = this.repository.getTotalAnnouncements();
		Integer totalInvestor = this.repository.getTotalInvestorRecords();
		Integer totalCompany = this.repository.getTotalCompanyRecords();
		Double minimumRequest = this.repository.getMinimumRequest();
		Double minimumOffers = this.repository.getMinimumOffers();
		Double maximumRequest = this.repository.getMaximumRequest();
		Double maximumOffers = this.repository.getMaximumOffers();
		Double averageRequest = this.repository.getAverageRequest();
		Double averageOffers = this.repository.getAverageOffers();
		Double desviationRequest = this.repository.getDesviationRequest();
		Double desviationOffers = this.repository.getDesviationOffers();
		Double averageJobsPerEmployer = this.repository.averageNumberJobsPerEmployer();
		Double averageApplicationPerWorker = this.repository.averageNumberOfApplicationPerWorker();
		Double averageNumberOfApplicationPerEmployer = this.repository.averageNumberOfApplicationPerEmployer();

		Integer ratioOfDraftJobs = this.repository.ratioOfDraftJobs();
		Integer ratioOfPublishedJobs = this.repository.ratioOfPublishedJobs();
		Integer ratioOfAcceptedApplications = this.repository.ratioOfAcceptedApplications();
		Integer ratioOfRejectedApplications = this.repository.ratioOfRejectedApplications();
		Integer ratioOfPendingApplications = this.repository.ratioOfPendingApplications();

		Integer ratioOfAcceptedApplicationsInLast4Weeks = this.repository.ratioOfAcceptedApplicationsInLast4Weeks();
		Integer ratioOfRejectedApplicationsInLast4Weeks = this.repository.ratioOfRejectedApplicationsInLast4Weeks();
		Integer ratioOfPendingApplicationsInLast4Weeks = this.repository.ratioOfPendingApplicationsInLast4Weeks();

		request.unbind(entity, model, "labels", "numC", "numI");

		model.setAttribute("totalAnnouncements", totalAnnouncements);
		model.setAttribute("totalInvestor", totalInvestor);
		model.setAttribute("totalCompany", totalCompany);
		model.setAttribute("minimumRequest", minimumRequest);
		model.setAttribute("minimumOffers", minimumOffers);
		model.setAttribute("maximumOffers", maximumOffers);
		model.setAttribute("maximumRequest", maximumRequest);
		model.setAttribute("averageRequest", averageRequest);
		model.setAttribute("averageOffers", averageOffers);
		model.setAttribute("desviationRequest", desviationRequest);
		model.setAttribute("desviationOffers", desviationOffers);
		model.setAttribute("averageJobsPerEmployer", averageJobsPerEmployer);
		model.setAttribute("averageApplicationPerWorker", averageApplicationPerWorker);
		model.setAttribute("averageNumberOfApplicationPerEmployer", averageNumberOfApplicationPerEmployer);

		model.setAttribute("ratioOfDraftJobs", ratioOfDraftJobs);
		model.setAttribute("ratioOfPublishedJobs", ratioOfPublishedJobs);
		model.setAttribute("ratioOfAcceptedApplications", ratioOfAcceptedApplications);
		model.setAttribute("ratioOfRejectedApplications", ratioOfRejectedApplications);
		model.setAttribute("ratioOfPendingApplications", ratioOfPendingApplications);

		model.setAttribute("ratioOfAcceptedApplicationsInLast4Weeks", ratioOfAcceptedApplicationsInLast4Weeks);
		model.setAttribute("ratioOfRejectedApplicationsInLast4Weeks", ratioOfRejectedApplicationsInLast4Weeks);
		model.setAttribute("ratioOfPendingApplicationsInLast4Weeks", ratioOfPendingApplicationsInLast4Weeks);

		//Deliverable 05
		Date date = Date.valueOf(LocalDate.now().minusDays(28));

		System.out.println(date);

		List<String[]> rejected = this.repository.numberOfRejectedApplications(date);
		List<String[]> accepted = this.repository.numberOfAcceptedApplications(date);
		List<String[]> pending = this.repository.numberOfPendingApplications(date);

		List<LocalDate> allDate = this.sacaFechas(date);

		model.setAttribute("numberOfRejectedApplications", this.sacaDatosDeFecha(rejected, allDate));
		model.setAttribute("numberOfAcceptedApplications", this.sacaDatosDeFecha(accepted, allDate));
		model.setAttribute("numberOfPendingApplications", this.sacaDatosDeFecha(pending, allDate));

	}

	private List<Integer> sacaDatosDeFecha(final List<String[]> lista, final List<LocalDate> allDate) {
		Map<LocalDate, Integer> res = new HashMap<LocalDate, Integer>();

		List<String[]> l = lista;
		for (LocalDate fecha : allDate) {
			res.put(fecha, 0);
		}

		for (int i = 0; i < lista.size(); i++) {
			LocalDate date = LocalDate.parse(lista.get(i)[0].substring(0, 10));
			if (res.containsKey(date)) {
				res.replace(date, 0, Integer.parseInt(l.get(i)[1]));
			}
		}

		TreeMap<LocalDate, Integer> result = new TreeMap<>(res);

		List<Integer> result2 = result.values().stream().collect(Collectors.toList());

		return result2;
	}

	private List<LocalDate> sacaFechas(final Date date) {
		Date fechaActual = Date.valueOf(LocalDate.now());
		long numeroDeDias = (fechaActual.getTime() - date.getTime()) / (1000 * 60 * 60 * 24);
		List<LocalDate> todasFechas = IntStream.iterate(0, x -> x + 1).limit(numeroDeDias).mapToObj(x -> date.toLocalDate().plusDays(x)).collect(Collectors.toList());
		return todasFechas;
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result = new Dashboard();

		Collection<Object[]> com = this.repository.findAllCompanies();
		Collection<Object[]> inv = this.repository.findAllInvestors();
		int i = 0;
		String op;

		String[] numC;
		String[] numI;

		for (Object[] o : com) {

			if (ArrayUtils.contains(result.getLabels(), o[0].toString())) {
				i = ArrayUtils.lastIndexOf(result.getLabels(), o[0].toString());
				numC = result.getNumC();
				op = Integer.toString(Integer.parseInt(numC[i]) + Integer.parseInt(o[1].toString()));
				result.setNumI(ArrayUtils.remove(result.getNumC(), i));
				result.setNumC(ArrayUtils.insert(i, result.getNumC(), op));
			}

			if (!ArrayUtils.contains(result.getLabels(), o[0].toString())) {
				result.setLabels(ArrayUtils.add(result.getLabels(), o[0].toString()));
				result.setNumC(ArrayUtils.add(result.getNumC(), o[1].toString()));
				result.setNumI(ArrayUtils.add(result.getNumI(), "0"));

			}
		}

		for (Object[] o : inv) {
			if (ArrayUtils.contains(result.getLabels(), o[0].toString())) {
				i = ArrayUtils.lastIndexOf(result.getLabels(), o[0].toString());
				numI = result.getNumI();
				op = Integer.toString(Integer.parseInt(numI[i]) + Integer.parseInt(o[1].toString()));
				result.setNumI(ArrayUtils.remove(result.getNumI(), i));
				result.setNumI(ArrayUtils.insert(i, result.getNumI(), op));
			}

			if (!ArrayUtils.contains(result.getLabels(), o[0].toString())) {
				result.setLabels(ArrayUtils.add(result.getLabels(), o[0].toString()));
				result.setNumI(ArrayUtils.add(result.getNumI(), o[1].toString()));
				result.setNumC(ArrayUtils.add(result.getNumC(), "0"));

			}
		}

		return result;
	}

}
