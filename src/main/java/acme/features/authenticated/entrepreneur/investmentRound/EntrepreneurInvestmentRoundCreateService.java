
package acme.features.authenticated.entrepreneur.investmentRound;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurInvestmentRoundCreateService implements AbstractCreateService<Entrepreneur, InvestmentRound> {

	@Autowired
	private EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation", "active", "status");
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "kindRound", "description", "amount", "link");

	}

	@Override
	public InvestmentRound instantiate(final Request<InvestmentRound> request) {
		InvestmentRound res;
		int entrepreneurId = request.getPrincipal().getAccountId();
		Entrepreneur entrepreneur = this.repository.findEntrepreneurById(entrepreneurId);

		res = new InvestmentRound();
		res.setEntrepreneur(entrepreneur);
		res.setActive(false);
		res.setStatus("Draft");

		return res;
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
				if (EntrepreneurInvestmentRoundCreateService.esMayuscula(primero) == false) {
					ayuda = false;
				}
			} else if (priAux.length() == 2) {
				primero = priAux.substring(0, 2) + "X";
				if (EntrepreneurInvestmentRoundCreateService.esMayuscula(primero) == false) {
					ayuda = false;
				}
			} else {
				primero = priAux.substring(0, 1) + "XX";
				if (EntrepreneurInvestmentRoundCreateService.esMayuscula(primero) == false) {
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

		if (porcentajeSpam >= custom.getSpamThreshold()) {
			errors.state(request, false, "active", "entrepreneur.investmentRound.porcenspam");
		}
	}

	@Override
	public void create(final Request<InvestmentRound> request, final InvestmentRound entity) {

		Date creation;
		creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);
		this.repository.save(entity);
	}

}
