
package acme.features.administrator.nonCommercial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.nonCommercial.NonCommercial;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/non-commercial/")
public class AdministratorNonCommercialController extends AbstractController<Administrator, NonCommercial> {

	@Autowired
	private AdministratorNonCommercialListService	listService;

	@Autowired
	private AdministratorNonCommercialCreateService	createService;

	@Autowired
	private AdministratorNonCommercialDeleteService	deleteService;

	@Autowired
	private AdministratorNonCommercialShowService	showService;

	@Autowired
	private AdministratorNonCommercialUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);

	}

}
