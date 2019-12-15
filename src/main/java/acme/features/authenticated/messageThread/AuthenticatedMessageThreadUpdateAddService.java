
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.MessageThread;
import acme.entities.messageThread.MessageThreadAuthenticated;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedMessageThreadUpdateAddService implements AbstractUpdateService<Authenticated, MessageThread> {

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

		request.bind(entity, errors, "title", "creationMoment", "message");
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result;
		MessageThreadAuthenticated mta;

		String id;
		id = request.getServletRequest().getParameter("id");

		mta = this.repository.findOneMessageThreadAuthenticatedById(Integer.parseInt(id));

		result = this.repository.findOneById(mta.getThread().getId());

		return result;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<MessageThread> request, final MessageThread entity) {
		assert request != null;
		assert entity != null;

		MessageThreadAuthenticated messageThreadAuthenticated;
		String username;
		String email;
		Collection<MessageThreadAuthenticated> mtas;

		username = request.getModel().getString("username");
		email = request.getModel().getString("email");
		UserAccount user = this.repository.findOneUserAccountByUsernameEmail(username, email);

		messageThreadAuthenticated = new MessageThreadAuthenticated();
		messageThreadAuthenticated.setUser(user);
		messageThreadAuthenticated.setThread(entity);

		this.repository.save(messageThreadAuthenticated);

		mtas = this.repository.findManyMessageThreadAuthenticatedByMTId(entity.getId());
		mtas.add(messageThreadAuthenticated);
		entity.setUsers(mtas);

		this.repository.save(entity);
	}

}
