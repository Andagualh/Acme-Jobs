
package acme.features.employer.descriptor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.descriptor.Descriptor;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/descriptor/")
public class EmployerDescriptorController extends AbstractController<Employer, Descriptor> {

	//Internal State -------------------------------------

	@Autowired
	private EmployerDescriptorShowService	showService;

	@Autowired
	private EmployerDescriptorListService	listService;

	@Autowired
	private EmployerDescriptorUpdateService	updateService;

	@Autowired
	private EmployerDescriptorCreateService	createService;


	//Costructor -----------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
