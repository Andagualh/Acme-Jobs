
package acme.features.authenticated.messageThread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
public class AuthenticatedMessageThreadCreateService implements AbstractCreateService<Authenticated, MessageThread> {

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");
	}

	@Override
	public MessageThread instantiate(final Request<MessageThread> request) {
		MessageThread result;
		Date moment;
		Collection<Authenticated> users;
		Collection<Message> messages;

		users = new ArrayList<Authenticated>();
		messages = new ArrayList<Message>();
		moment = new Date(System.currentTimeMillis() - 1);

		Integer id = request.getPrincipal().getActiveRoleId();

		Authenticated userCreator = this.repository.findUserAccountById(id);

		users.add(userCreator);

		result = new MessageThread();

		result.setCreationMoment(moment);
		result.setUsers(users);
		result.setMessage(messages);

		return result;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<MessageThread> request, final MessageThread entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
