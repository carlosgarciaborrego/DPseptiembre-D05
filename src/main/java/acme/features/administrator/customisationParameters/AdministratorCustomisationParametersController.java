 

package acme.features.administrator.customisationParameters;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/customisation-parameters/")
public class AdministratorCustomisationParametersController extends AbstractController<Administrator, CustomisationParameters> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorCustomisationParametersShowService		showService;

	@Autowired
	private AdministratorCustomisationParametersUpdateService	updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
