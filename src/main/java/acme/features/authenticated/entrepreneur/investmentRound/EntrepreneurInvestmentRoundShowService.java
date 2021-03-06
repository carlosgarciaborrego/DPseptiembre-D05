
package acme.features.authenticated.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.features.authenticated.entrepreneur.application.EntrepreneurApplicationRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurInvestmentRoundShowService implements AbstractShowService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	EntrepreneurApplicationRepository		repoApp;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		boolean result;
		int investmentRoundId;
		InvestmentRound investmentRound;
		Entrepreneur entrepreneur;
		Principal principal;

		investmentRoundId = request.getModel().getInteger("id");
		investmentRound = this.repository.findOneById(investmentRoundId);
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();

		result = investmentRound.getActive() || !investmentRound.getActive() && entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<Application> tieneApps = this.repoApp.findApplicationToInvest(entity.getId());
		if (tieneApps.isEmpty()) {
			entity.setHasApp(false);
		} else {
			entity.setHasApp(true);
		}

		request.unbind(entity, model, "ticker", "creation", "kindRound", "title", "description", "amount", "link", "active", "hasApp", "isInvestor", "entrepreneur");
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {

		assert request != null;

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
