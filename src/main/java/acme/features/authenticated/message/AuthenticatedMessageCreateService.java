
package acme.features.authenticated.message;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message.Message;
import acme.entities.messageThread.MessageThread;
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
	}

	@Override
	public Message instantiate(final Request<Message> request) {
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

		accepted = request.getModel().getBoolean("accepted");

		if (!errors.hasErrors("accepted")) {
			errors.state(request, accepted, "accepted", "authenticated.message.accepted");
		}
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
