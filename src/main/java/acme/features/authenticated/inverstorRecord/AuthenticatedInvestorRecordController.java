
package acme.features.authenticated.inverstorRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investorRecord.InvestorRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/investor-record/")
public class AuthenticatedInvestorRecordController extends AbstractController<Authenticated, InvestorRecord> {

	// Internal state ---------------------------------

	@Autowired
	private AuthenticatedInvestorRecordListService		listService;

	@Autowired
	private AuthenticatedInvestorRecordShowService		showService;

	@Autowired
	private AuthenticatedInvestorRecordTopListService	topListService;


	//Constructor -------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.TOPLIST, BasicCommand.LIST, this.topListService);
	}

}
