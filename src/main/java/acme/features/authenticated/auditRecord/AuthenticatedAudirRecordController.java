
package acme.features.authenticated.auditRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.auditRecord.AuditRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/audit-record/")
public class AuthenticatedAudirRecordController extends AbstractController<Authenticated, AuditRecord> {

	//Internal State -------------------------------------

	@Autowired
	private AuthenticatedJAuditRecordShowService	showService;

	@Autowired
	private AuthenticatedAuditRecordListService		listService;


	//Costructor -----------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}

}
