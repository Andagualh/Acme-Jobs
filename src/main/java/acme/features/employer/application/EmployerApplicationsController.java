
package acme.features.employer.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.application.Application;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/application")
public class EmployerApplicationsController extends AbstractController<Employer, Application> {

	//Internal state

	@Autowired
	private EmployerApplicationsListService					listMineService;

	@Autowired
	private EmployerApplicationsListByRefService			listMineByRefService;

	@Autowired
	private EmployerApplicationsListByStatusService			listMineByStatusService;

	@Autowired
	private EmployerApplicationsListByCreationMomentService	listMineByCreationMomentService;

	@Autowired
	private EmployerApplicationsShowService					showService;

	@Autowired
	private EmployerApplicationsUpdateService				updateService;


	//Constructors

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addCustomCommand(CustomCommand.LIST_REF, BasicCommand.LIST, this.listMineByRefService);
		super.addCustomCommand(CustomCommand.LIST_STATUS, BasicCommand.LIST, this.listMineByStatusService);
		super.addCustomCommand(CustomCommand.LIST_CREATIONMOMENT, BasicCommand.LIST, this.listMineByCreationMomentService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
