
package acme.features.administrator.announcements;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import acme.entities.announcements.Announcements;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping(value = "/administrator/announcements/", method = RequestMethod.POST)
public class AdministratorAnnouncementController extends AbstractController<Administrator, Announcements> {

	//Internal State

	@Autowired
	private AdministratorAnnouncementListService	listService;

	@Autowired
	private AdministratorAnnouncementShowService	showService;

	@Autowired
	private AdministratorAnnouncementUpdateService	updateService;

	@Autowired
	private AdministratorAnnouncementDeleteService	deleteService;

	@Autowired
	private AdministratorAnnouncementCreateService	createService;


	//Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
