
package acme.features.authenticated.investor.application;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorApplicationRepository extends AbstractRepository {

	@Query("select i from Application i where i.id = ?1")
	Application findOneById(int id);

	@Query("select i from Application i where i.investor.id = ?1")
	Collection<Application> findApplicationMine(int id);

	@Query("select a.id from InvestmentRound a where a.active = true")
	List<Integer> findIdInvestmentRoundActive();

	@Query("select investor from Investor investor where investor.userAccount.id = ?1")
	Investor findInvestor(int id);

	@Query("select j from InvestmentRound j where j.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);

	@Query("select ua from Application ua where ua.ticker = ?1")
	Application findByTicker(String ticker);
}
