
package acme.features.administrator.commercial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.commercial.Commercial;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/commercial/")
public class AdministratorCommercialController extends AbstractController<Administrator, Commercial> {

	@Autowired
	private AdministratorCommercialListService		listService;

	@Autowired
	private AdministratorCommercialShowService		showService;

	@Autowired
	private AdministratorCommercialCreateService	createService;

	@Autowired
	private AdministratorCommercialDeleteService	deleteService;

	@Autowired
	private AdministratorCommercialUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
