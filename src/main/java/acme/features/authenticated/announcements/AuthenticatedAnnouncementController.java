
package acme.features.authenticated.announcements;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.announcements.Announcements;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/announcements/")
public class AuthenticatedAnnouncementController extends AbstractController<Authenticated, Announcements> {

	//Internal State

	@Autowired
	private AuthenticatedAnnouncementListService	listService;

	@Autowired
	private AuthenticatedAnnouncementShowService	showService;


	//Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
