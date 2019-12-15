
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
public class AuthenticatedMessageThreadUpdateService implements AbstractUpdateService<Authenticated, MessageThread> {

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

		request.bind(entity, errors, "message", "administrator");
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment");
	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
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

		MessageThreadAuthenticated newMessageThreadAuthenticated;
		String usernameAdd;
		String emailAdd;
		String usernameDelete;
		String emailDelete;
		Collection<MessageThreadAuthenticated> mtas;

		usernameAdd = request.getModel().getString("usernameAdd");
		emailAdd = request.getModel().getString("emailAdd");
		UserAccount userAdd = this.repository.findOneUserAccountByUsernameEmail(usernameAdd, emailAdd);

		usernameDelete = request.getModel().getString("usernameDelete");
		emailDelete = request.getModel().getString("emailDelete");
		UserAccount userDelete = this.repository.findOneUserAccountByUsernameEmail(usernameDelete, emailDelete);

		if (userAdd != null) {
			newMessageThreadAuthenticated = new MessageThreadAuthenticated();
			newMessageThreadAuthenticated.setUser(userAdd);
			newMessageThreadAuthenticated.setThread(entity);

			this.repository.save(newMessageThreadAuthenticated);

			mtas = this.repository.findManyMessageThreadAuthenticatedByMTId(entity.getId());
			mtas.add(newMessageThreadAuthenticated);
			entity.setUsers(mtas);
		}

		if (userDelete != null) {
			MessageThreadAuthenticated oldMessageThreadAuthenticated = this.repository.findOneMessageThreadAuthenticatedById(userDelete.getId());

			this.repository.delete(oldMessageThreadAuthenticated);

			mtas = this.repository.findManyMessageThreadAuthenticatedByMTId(entity.getId());
			mtas.remove(oldMessageThreadAuthenticated);
			entity.setUsers(mtas);
		}

		this.repository.save(entity);
	}

}
