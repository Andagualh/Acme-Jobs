
package acme.features.administrator.investorRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investorRecord.InvestorRecord;
import acme.entities.spamlist.Spamlist;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorInvestorRecordUpdateService implements AbstractUpdateService<Administrator, InvestorRecord> {

	//Internal State

	@Autowired
	AdministratorInvestorRecordRepository repository;

	//Service Interface


	@Override
	public boolean authorise(final Request<InvestorRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<InvestorRecord> request, final InvestorRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<InvestorRecord> request, final InvestorRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "statement", "stars");

	}

	@Override
	public InvestorRecord findOne(final Request<InvestorRecord> request) {
		assert request != null;

		InvestorRecord result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<InvestorRecord> request, final InvestorRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Spamlist SpEN = this.repository.findEN("EN");
		Spamlist SpES = this.repository.findES("ES");

		if (!errors.hasErrors()) {
			errors.state(request, !entity.spam(SpEN) || !entity.spam(SpES), "name", "acme.validation.spamlist");
		}

	}

	@Override
	public void update(final Request<InvestorRecord> request, final InvestorRecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
