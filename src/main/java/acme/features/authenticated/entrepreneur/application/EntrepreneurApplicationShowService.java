
package acme.features.authenticated.entrepreneur.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurApplicationShowService implements AbstractShowService<Entrepreneur, Application> {

	@Autowired
	EntrepreneurApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int appId;
		Application app;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;

		appId = request.getModel().getInteger("id");
		app = this.repository.findOneById(appId);
		investmentRound = app.getInvestmentRound();
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();

		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();
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
