
package acme.features.anonymous.announcements;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.announcements.Announcements;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/announcements/")
public class AnonymousAnnouncementController extends AbstractController<Anonymous, Announcements> {

	//Internal State

	@Autowired
	private AnonymousAnnouncementListService	listService;

	@Autowired
	private AnonymousAnnouncementShowService	showService;


	//Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
