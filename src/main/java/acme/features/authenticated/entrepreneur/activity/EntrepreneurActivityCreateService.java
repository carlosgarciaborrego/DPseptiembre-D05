
package acme.features.authenticated.entrepreneur.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurActivityCreateService implements AbstractCreateService<Entrepreneur, Activity> {

	@Autowired
	private EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		return true;
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
		model.setAttribute("id", entity.getInvestmentRound().getId());

	}

	@Override
	public Activity instantiate(final Request<Activity> request) {
		Activity res;
		res = new Activity();
		int idInvestmentRound = request.getModel().getInteger("id");
		InvestmentRound investmentRound = this.repository.findInvestmentRoundById(idInvestmentRound);
		if (investmentRound != null) {
			res.setInvestmentRound(investmentRound);
		}

		return res;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//validate de budget
	}

	@Override
	public void create(final Request<Activity> request, final Activity entity) {

		this.repository.save(entity);
	}

}
