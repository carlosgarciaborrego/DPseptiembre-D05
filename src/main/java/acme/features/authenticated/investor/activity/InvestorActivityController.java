
package acme.features.authenticated.investor.activity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.activities.Activity;
import acme.entities.roles.Investor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/investor/activity/")
public class InvestorActivityController extends AbstractController<Investor, Activity> {

	@Autowired
	private InvestorActivityListService	listService;

	@Autowired
	private InvestorActivityShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
