
package acme.features.authenticated.entrepreneur.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurActivityUpdateService implements AbstractUpdateService<Entrepreneur, Activity> {

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;
		boolean result;
		int investmentRoundId;
		Activity activity;
		InvestmentRound investmentRound;
		Principal principal;
		Entrepreneur entrepreneur;

		investmentRoundId = request.getModel().getInteger("id");
		activity = this.repository.findOneById(investmentRoundId);
		investmentRound = activity.getInvestmentRound();
		entrepreneur = investmentRound.getEntrepreneur();
		principal = request.getPrincipal();

		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "startDate", "endDate", "budget");

	}

	@Override
	public Activity findOne(final Request<Activity> request) {
		assert request != null;

		Activity result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
