
package acme.features.authenticated.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

}
