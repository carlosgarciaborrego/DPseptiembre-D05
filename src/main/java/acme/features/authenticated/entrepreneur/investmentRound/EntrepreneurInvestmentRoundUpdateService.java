
package acme.features.authenticated.entrepreneur.investmentRound;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


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

		result = entrepreneur.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "kindRound", "title", "description", "amount", "link", "active");

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
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean kindRoundCorrect = false;
		String[] kinds = {
			"SEED", "ANGEL", "SERIES-A", "SERIES-B", "SERIES-C", "BRIDGE"
		};

		for (String k : kinds) {
			if (entity.getKindRound().equals(k)) {
				kindRoundCorrect = true;
				break;
			} else {
				kindRoundCorrect = false;
			}
		}
		errors.state(request, kindRoundCorrect, "kindRound", "entrepreneur.investmentRound.kindRoundCorrect");

		boolean tickerOK = false;

		if (entity.getTicker() != null && entity.getTicker().length() == 13) {
			String priAux = entity.getEntrepreneur().getActivitySector().toUpperCase();
			String primero = "";
			Date segAux = new Date(System.currentTimeMillis() - 1);
			String segundo = segAux.toString().substring(27, 29);
			String tercero = entity.getTicker().substring(7, 13);
			boolean ayuda = true;

			if (priAux.length() >= 3) {
				primero = priAux.substring(0, 3);
				if (EntrepreneurInvestmentRoundUpdateService.esMayuscula(primero) == false) {
					ayuda = false;
				}
			} else if (priAux.length() == 2) {
				primero = priAux.substring(0, 2) + "X";
				if (EntrepreneurInvestmentRoundUpdateService.esMayuscula(primero) == false) {
					ayuda = false;
				}
			} else {
				primero = priAux.substring(0, 1) + "XX";
				if (EntrepreneurInvestmentRoundUpdateService.esMayuscula(primero) == false) {
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

	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
