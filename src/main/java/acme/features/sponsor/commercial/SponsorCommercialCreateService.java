
package acme.features.sponsor.commercial;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercial.Commercial;
import acme.entities.roles.Sponsor;
import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCommercialCreateService implements AbstractCreateService<Sponsor, Commercial> {

	@Autowired
	SponsorCommercialRepository repository;


	@Override
	public boolean authorise(final Request<Commercial> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Commercial> request, final Commercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Commercial> request, final Commercial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("number", "");
		} else {
			request.transfer(model, "status");
		}

		request.unbind(entity, model, "banner", "slogan", "url");
	}

	@Override
	public Commercial instantiate(final Request<Commercial> request) {
		Commercial result;
		Integer id;
		Sponsor sponsor;

		result = new Commercial();
		id = request.getPrincipal().getActiveRoleId();
		sponsor = this.repository.findSponsorById(id);

		result.setSponsor(sponsor);
		result.setCard(sponsor.getCard());

		return result;
	}

	@Override
	public void validate(final Request<Commercial> request, final Commercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean creditCard = this.repository.findSponsorById(request.getPrincipal().getActiveRoleId()).getCard() == null;

		errors.state(request, !creditCard, "banner", "acme.validation.card");

		Boolean isSpamEN, isSpamES;
		String reallyBigString;
		reallyBigString = request.getModel().getString("banner") + " " + request.getModel().getString("slogan") + " " + request.getModel().getString("slogan") + " " + request.getModel().getString("url");
		Spamlist spamEN = this.repository.findSpamLists("EN");
		Spamlist spamES = this.repository.findSpamLists("ES");

		isSpamEN = this.isSpam(reallyBigString, spamEN, entity);
		isSpamES = this.isSpam(reallyBigString, spamES, entity);

		errors.state(request, !isSpamEN || !isSpamES, "banner", "acme.validation.spam");

	}

	@Override
	public void create(final Request<Commercial> request, final Commercial entity) {
		assert request != null;

		this.repository.save(entity);
	}

	//Método Auxiliar

	private Boolean isSpam(final String reallyBigString, final Spamlist sl, final Commercial entity) {

		Collection<Spamword> spamwords = sl.getSpamwordslist();

		Double numSpamWords = 0.;

		for (Spamword sw : spamwords) {
			String spamword = sw.getSpamword();
			numSpamWords = numSpamWords + this.numDeSpamwords(reallyBigString.toLowerCase(), spamword, 0.);
		}

		return numSpamWords / 100 > sl.getThreshold();
	}

	private Double numDeSpamwords(final String fullText, final String spamword, final Double u) {
		if (!fullText.contains(spamword)) {
			return u;
		} else {
			Integer a = fullText.indexOf(spamword);
			return this.numDeSpamwords(fullText.substring(a + 1), spamword, u + 1);
		}
	}

}
