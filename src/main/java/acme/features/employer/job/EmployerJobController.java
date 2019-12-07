
package acme.features.employer.job;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.job.Job;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/job/")
public class EmployerJobController extends AbstractController<Employer, Job> {

	//Internal State -------------------------------------

	@Autowired
	private EmployerJobShowService		showService;

	@Autowired
	private EmployerJobListMineService	listMineService;


	//Costructor -----------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
	}

}
