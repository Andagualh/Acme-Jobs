
package acme.features.administrator.investorRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import acme.components.CustomCommand;
import acme.entities.investorRecord.InvestorRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping(value = "/administrator/investor-record/", method = RequestMethod.POST)
public class AdministratorInvestorRecordController extends AbstractController<Administrator, InvestorRecord> {

	// Internal state ---------------------------------

	@Autowired
	private AdministratorInvestorRecordListService		listService;

	@Autowired
	private AdministratorInvestorRecordShowService		showService;

	@Autowired
	private AdministratorInvestorRecordTopListService	topListService;

	@Autowired
	private AdministratorInvestorRecordDeleteService	deleteService;

	@Autowired
	private AdministratorInvestorRecordUpdateService	updateService;

	@Autowired
	private AdministratorInvestorRecordCreateService	createService;


	//Constructor -------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.TOPLIST, BasicCommand.LIST, this.topListService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
