
package acme.features.sponsor.noncommercial;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.nonCommercial.NonCommercial;
import acme.entities.roles.Sponsor;
import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorNonCommercialUpdateService implements AbstractUpdateService<Sponsor, NonCommercial> {

	@Autowired
	SponsorNonCommercialRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercial> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<NonCommercial> request, final NonCommercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<NonCommercial> request, final NonCommercial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "banner", "slogan", "url", "jingle");
	}

	@Override
	public NonCommercial findOne(final Request<NonCommercial> request) {
		assert request != null;

		NonCommercial result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneNonCommercialBannerById(id);
		return result;
	}

	@Override
	public void validate(final Request<NonCommercial> request, final NonCommercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean isSpamEN, isSpamES;
		String reallyBigString;
		reallyBigString = request.getModel().getString("slogan");
		Spamlist spamEN = this.repository.findSpamLists("EN");
		Spamlist spamES = this.repository.findSpamLists("ES");

		isSpamEN = this.isSpam(reallyBigString, spamEN, entity);
		isSpamES = this.isSpam(reallyBigString, spamES, entity);

		errors.state(request, !isSpamEN || !isSpamES, "banner", "acme.validation.spam");

	}

	@Override
	public void update(final Request<NonCommercial> request, final NonCommercial entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	//Método Auxiliar

	private Boolean isSpam(final String reallyBigString, final Spamlist sl, final NonCommercial entity) {

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
