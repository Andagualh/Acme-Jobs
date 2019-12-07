
package acme.features.administrator.challenge;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.challenge.Challenge;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/challenge/")
public class AdministratorChallengeController extends AbstractController<Administrator, Challenge> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorChallengeListService	listService;

	@Autowired
	private AdministratorChallengeShowService	showService;

	@Autowired
	private AdministratorChallengeDeleteService	deleteService;

	@Autowired
	private AdministratorChallengeCreateService	createService;

	@Autowired
	private AdministratorChallengeUpdateService	updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
