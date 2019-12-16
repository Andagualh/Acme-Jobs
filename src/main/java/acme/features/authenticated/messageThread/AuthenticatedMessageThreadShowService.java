
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message.Message;
import acme.entities.messageThread.MessageThread;
import acme.entities.messageThread.MessageThreadAuthenticated;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageThreadShowService implements AbstractShowService<Authenticated, MessageThread> {

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		Boolean result = true;

		Integer idPrincipal = request.getPrincipal().getAccountId();
		Integer idThread = request.getModel().getInteger("id");

		MessageThreadAuthenticated mta = this.repository.findOneMessageThreadAuthenticatedByIds(idPrincipal, idThread);

		if (mta == null) {
			result = false;
		}

		return result;
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Principal principal = request.getPrincipal();

		Integer id = principal.getAccountId();

		model.setAttribute("id", id);

		request.unbind(entity, model, "title", "creationMoment", "message");

		Collection<Message> messages = this.repository.findManyMessagesById(entity.getId());

		model.setAttribute("message", messages);

		model.setAttribute("isAdministrator", entity.getAdministrator().getId() == request.getPrincipal().getAccountId());
	}
	@Override
	public MessageThread findOne(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findMessageThreadById(id);

		return result;
	}

}
