
package acme.features.sponsor.commercial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.commercial.Commercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/commercial")
public class SponsorCommercialController extends AbstractController<Sponsor, Commercial> {

	@Autowired
	private SponsorCommercialListService	listService;

	@Autowired
	private SponsorCommercialShowService	showService;

	@Autowired
	private SponsorCommercialCreateService	createService;

	@Autowired
	private SponsorCommercialUpdateService	updateService;

	@Autowired
	private SponsorCommercialDeleteService	deleteService;


	@PostConstruct
	public void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
