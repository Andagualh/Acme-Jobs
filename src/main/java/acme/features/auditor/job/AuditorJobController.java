
package acme.features.auditor.job;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.job.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/job/")
public class AuditorJobController extends AbstractController<Auditor, Job> {

	//Internal State -------------------------------------

	@Autowired
	private AuditorJobShowService				showService;

	@Autowired
	private AuditorRecordedJobListService		recordedListService;

	@Autowired
	private AuditorNonRecordedJobListService	nonRecordedListService;


	//Costructor -----------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_REC, BasicCommand.LIST, this.recordedListService);
		super.addCustomCommand(CustomCommand.LIST_NONREC, BasicCommand.LIST, this.nonRecordedListService);
	}

}
