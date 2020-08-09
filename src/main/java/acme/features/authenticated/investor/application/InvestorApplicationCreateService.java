
package acme.features.authenticated.investor.application;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	@Autowired
	InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int Idnuevo;
		List<Integer> idAppFM;

		Idnuevo = request.getModel().getInteger("id");
		idAppFM = this.repository.findIdInvestmentRoundActive();
		if (idAppFM.contains(Idnuevo)) {
			result = true;
		} else {
			result = false;
		}

		return result;
	}

	public boolean isNumberInteger(final String numero) {
		try {
			Integer.parseInt(numero);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean esMayuscula(final String s) {
		return s.equals(s.toUpperCase());
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean tickerOK = false;

		if (entity.getTicker() != null && entity.getTicker().length() == 13) {
			String priAux = entity.getInvestor().getActivitySector().toUpperCase();
			String primero = "";
			Date segAux = new Date(System.currentTimeMillis() - 1);
			String segundo = segAux.toString().substring(27, 29);
			String tercero = entity.getTicker().substring(7, 13);
			boolean ayuda = true;

			if (priAux.length() >= 3) {
				primero = priAux.substring(0, 3);
				if (InvestorApplicationCreateService.esMayuscula(primero) == false) {
					ayuda = false;
				}
			} else if (priAux.length() == 2) {
				primero = priAux.substring(0, 2) + "X";
				if (InvestorApplicationCreateService.esMayuscula(primero) == false) {
					ayuda = false;
				}
			} else {
				primero = priAux.substring(0, 1) + "XX";
				if (InvestorApplicationCreateService.esMayuscula(primero) == false) {
					ayuda = false;
				}
			}

			if (ayuda == true && entity.getTicker().substring(0, 3).equals(primero) && entity.getTicker().substring(3, 4).equals("-") && entity.getTicker().substring(4, 6).equals(segundo) && entity.getTicker().substring(6, 7).equals("-")
				&& this.isNumberInteger(tercero) == true) {
				tickerOK = true;
			} else {
				tickerOK = false;
			}

		}

		errors.state(request, tickerOK, "ticker", "entrepreneur.investmentRound.ticker");

		boolean isDuplicated = false;
		isDuplicated = this.repository.findByTicker(entity.getTicker()) != null;
		errors.state(request, !isDuplicated, "ticker", "entrepreneur.investmentRound.duplicatedTicker");

	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation", "status", "answer");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "statement", "offer");
		model.setAttribute("id", entity.getInvestmentRound().getId());
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application res = new Application();
		int userAccountId = request.getPrincipal().getAccountId();
		Investor investor = this.repository.findInvestor(userAccountId);

		InvestmentRound investmentRound = this.repository.findInvestmentRoundById(request.getModel().getInteger("id"));
		res.setInvestmentRound(investmentRound);
		res.setInvestor(investor);

		String answer = "";
		String status = "Pending";

		res.setAnswer(answer);
		res.setStatus(status);

		return res;

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(moment);

		this.repository.save(entity);
	}

}
