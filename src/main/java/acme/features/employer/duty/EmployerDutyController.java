
package acme.features.employer.duty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.duty.Duty;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/employer/duty/")
public class EmployerDutyController extends AbstractController<Authenticated, Duty> {

	//Internal State -------------------------------------

	@Autowired
	private EmployerDutyShowService	showService;

	@Autowired
	private EmployerDutyListService	listService;


	//Costructor -----------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}

}
