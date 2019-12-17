
package acme.features.authenticated.message;

import java.sql.Date;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message.Message;
import acme.entities.messageThread.MessageThread;
import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "tags", "body");

		model.setAttribute("id", request.getServletRequest().getParameter("id"));

		model.setAttribute("accepted", false);
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Message result;
		Date creationMoment;
		String id;

		result = new Message();
		creationMoment = new Date(System.currentTimeMillis() - 1);
		id = request.getServletRequest().getParameter("id");
		MessageThread mt = this.repository.findOneMessageThreadById(Integer.parseInt(id));

		result.setCreationMoment(creationMoment);
		result.setThread(mt);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Boolean accepted;
		Boolean spamES;
		Boolean spamEN;
		Spamlist SlEN;
		Spamlist SlES;

		SlEN = this.repository.findIdiom("EN");
		SlES = this.repository.findIdiom("ES");

		spamES = this.isSpam(SlEN, entity);
		spamEN = this.isSpam(SlES, entity);

		accepted = request.getModel().getBoolean("accepted");

		if (!errors.hasErrors("accepted")) {
			errors.state(request, accepted, "accepted", "authenticated.message.accepted");
		}

		if (!errors.hasErrors()) {
			errors.state(request, !spamEN, "title", "authenticated.message.spam");
			errors.state(request, !spamES, "title", "authenticated.message.spam");
		}
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	private Boolean isSpam(final Spamlist sl, final Message entity) {
		String fullText = entity.getTitle() + " " + entity.getTags() + " " + entity.getBody();

		Collection<Spamword> spamwords = sl.getSpamwordslist();

		Double numSpamWords = 0.;

		for (Spamword sw : spamwords) {
			String spamword = sw.getSpamword();
			numSpamWords = numSpamWords + this.numDeSpamwords(fullText, spamword, 0.);
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
