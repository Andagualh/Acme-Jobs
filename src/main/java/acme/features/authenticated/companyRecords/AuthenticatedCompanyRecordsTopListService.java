
package acme.features.authenticated.companyRecords;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companyRecords.CompanyRecords;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedCompanyRecordsTopListService implements AbstractListService<Authenticated, CompanyRecords> {

	@Autowired
	AuthenticatedCompanyRecordsRepository repository;


	@Override
	public boolean authorise(final Request<CompanyRecords> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<CompanyRecords> request, final CompanyRecords entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "description");

	}

	@Override
	public Collection<CompanyRecords> findMany(final Request<CompanyRecords> request) {
		assert request != null;

		Collection<CompanyRecords> result;
		Collection<CompanyRecords> res = new ArrayList<>();

		result = this.repository.findManyAll();
		for (CompanyRecords c : result) {
			if (c.getIncorporated() == true) {
				c.setName(c.getName() + ".Inc");
			} else if (c.getIncorporated() == false) {
				c.setName(c.getName() + ".LLC");
			}
			if (c.getStars() == 5) {
				res.add(c);
			}
		}
		return res;
	}

}
