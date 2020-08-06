
package acme.features.authenticated.investor.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.roles.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class InvestorActivityShowService implements AbstractShowService<Investor, Activity> {

	@Autowired
	InvestorActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;
		boolean result = true;

		return result;
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
}
