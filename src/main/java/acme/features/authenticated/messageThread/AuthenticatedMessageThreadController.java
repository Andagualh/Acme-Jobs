
package acme.features.authenticated.messageThread;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.messageThread.MessageThread;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/message-thread/")
public class AuthenticatedMessageThreadController extends AbstractController<Authenticated, MessageThread> {

	@Autowired
	private AuthenticatedMessageThreadListService	listService;

	@Autowired
	private AuthenticatedMessageThreadShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
