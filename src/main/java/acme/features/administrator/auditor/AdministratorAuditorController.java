
package acme.features.administrator.auditor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/auditor/")
public class AdministratorAuditorController extends AbstractController<Administrator, Auditor> {

	@Autowired
	private AdministratorAuditorListService		listService;

	@Autowired
	private AdministratorAuditorUpdateService	updateService;

	@Autowired
	private AdministratorAuditorShowService		showService;


	@PostConstruct
	private void intialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

	}
}
