
package acme.features.authenticated.investor.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorApplicationShowService implements AbstractShowService<Investor, Application> {

	@Autowired
	InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int appId;
		Application app;
		Investor apinv;
		Principal principal;

		appId = request.getModel().getInteger("id");
		app = this.repository.findOneById(appId);
		apinv = app.getInvestor();
		principal = request.getPrincipal();

		result = apinv.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		String tickerInv = this.repository.findTickerOfInvestmentRoundbyIdApp(entity.getId());
		entity.setTickerOfInvest(tickerInv);

		request.unbind(entity, model, "ticker", "tickerOfInvest", "creation", "statement", "offer", "status", "answer", "investmentRound", "investor");
	}

	@Override
	public Application findOne(final Request<Application> request) {

		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
