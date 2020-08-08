
package acme.features.authenticated.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activities.Activity;
import acme.entities.applications.Application;
import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select i from InvestmentRound i where i.id = ?1")
	InvestmentRound findOneById(int id);

	@Query("select i from InvestmentRound i where i.entrepreneur.id = ?1")
	Collection<InvestmentRound> findInvestmentRoundRegistered(int id);

	@Query("select ua from Entrepreneur ua where ua.userAccount.id = ?1")
	Entrepreneur findEntrepreneurById(int id);

	@Query("select a from Activity a where a.investmentRound.id = ?1")
	Collection<Activity> findActivitiesByInvestmentRoundId(int id);

	@Query("select ua from InvestmentRound ua where ua.ticker = ?1")
	InvestmentRound findByTicker(String ticker);

	@Query("select ua from CustomisationParameters ua")
	CustomisationParameters findCustomParameters();

	@Query("select a from Application a where a.investmentRound.id = ?1")
	Collection<Application> findApplicationToThisInvestmentRound(int id);
}
