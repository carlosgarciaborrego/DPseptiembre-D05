
package acme.features.authenticated.investor.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorApplicationRepository extends AbstractRepository {

	@Query("select i from Application i where i.id = ?1")
	Application findOneById(int id);

	@Query("select i from Application i where i.investor.id = ?1")
	Collection<Application> findApplicationMine(int id);
}
