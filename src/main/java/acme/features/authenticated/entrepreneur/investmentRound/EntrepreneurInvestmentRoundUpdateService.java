
package acme.features.authenticated.entrepreneur.investmentRound;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activities.Activity;
import acme.entities.customisationParameters.CustomisationParameters;
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

		request.unbind(entity, model, "ticker", "kindRound", "title", "description", "amount", "link", "active", "hasApp", "isInvestor");

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

		if (entity.getKindRound() != null) {
			for (String k : kinds) {
				if (entity.getKindRound().equals(k)) {
					kindRoundCorrect = true;
					break;
				} else {
					kindRoundCorrect = false;
				}
			}
		}
		errors.state(request, kindRoundCorrect, "kindRound", "entrepreneur.investmentRound.kindRoundCorrect");

		boolean moneyOK = false;
		int idInvest = entity.getId();
		Double total = new Double(0);
		Collection<Activity> activities = this.repository.findActivitiesByInvestmentRoundId(idInvest);
		if (activities != null && !activities.isEmpty()) {
			for (Activity a : activities) {
				if (a.getBudget() != null) {
					total = total + a.getBudget().getAmount();
				}
			}

		}

		CustomisationParameters custom = this.repository.findCustomParameters();

		double contSpam = 0.0;
		double conPalabras = 0.0;
		String todasPalabras = "";
		todasPalabras = entity.getDescription() + " " + entity.getTitle();
		String[] arrayPalabras = todasPalabras.split(" ");
		List<String> listPalabras = Arrays.asList(arrayPalabras);
		if (custom != null && StringUtils.isNotBlank(custom.getSpamWordsEn())) {
			String engSpam = custom.getSpamWordsEn();
			String[] arraySpam = engSpam.split(",");
			List<String> listSpamEn = Arrays.asList(arraySpam);
			for (String l : listPalabras) {
				if (StringUtils.isNotBlank(l)) {
					conPalabras++;
					for (String s : listSpamEn) {
						if (l.trim().toLowerCase().equals(s.trim().toLowerCase())) {
							contSpam++;
						}

					}
				}
			}
		}
		if (custom != null && StringUtils.isNotBlank(custom.getSpamWordsEs())) {
			String engSpam = custom.getSpamWordsEs();
			String[] arraySpam = engSpam.split(",");
			List<String> listSpamEs = Arrays.asList(arraySpam);
			for (String l : listPalabras) {
				if (StringUtils.isNotBlank(l)) {
					for (String s : listSpamEs) {
						if (l.trim().toLowerCase().equals(s.trim().toLowerCase())) {
							if (StringUtils.isNotBlank(custom.getSpamWordsEn()) && !custom.getSpamWordsEn().contains(s.trim())) {
								contSpam++;
							}
						}

					}
				}
			}
		}
		Double porcentajeSpam = contSpam / conPalabras * 100;

		if (entity.getActive() != null && entity.getActive() == true) {

			if (porcentajeSpam >= custom.getSpamThreshold()) {
				errors.state(request, false, "active", "entrepreneur.investmentRound.porcenspam");
			}

			if (total.equals(entity.getAmount().getAmount())) {
				moneyOK = true;
			} else {
				moneyOK = false;
			}
			errors.state(request, moneyOK, "amount", "entrepreneur.investmentRound.money");

			if (activities.isEmpty()) {
				errors.state(request, false, "active", "entrepreneur.investmentRound.zero");
			}
		}
	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
