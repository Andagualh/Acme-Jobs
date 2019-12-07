
package acme.features.sponsor.noncommercial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.nonCommercial.NonCommercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/non-commercial")
public class SponsorNonCommercialController extends AbstractController<Sponsor, NonCommercial> {

	@Autowired
	private SponsorNonCommercialListService	listService;

	@Autowired
	private SponsorNonCommercialShowService	showService;


	@PostConstruct
	public void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

	}

}
