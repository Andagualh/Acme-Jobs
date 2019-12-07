
package acme.features.anonymous.inverstorRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investorRecord.InvestorRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/investor-record/")
public class AnonymousInvestorRecordController extends AbstractController<Anonymous, InvestorRecord> {

	// Internal state ---------------------------------

	@Autowired
	private AnonymousInvestorRecordListService		listService;

	@Autowired
	private AnonymousInvestorRecordShowService		showService;

	@Autowired
	private AnonymousInvestorRecordTopListService	topListService;


	//Constructor -------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.TOPLIST, BasicCommand.LIST, this.topListService);
	}

}
