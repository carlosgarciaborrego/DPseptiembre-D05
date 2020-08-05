
package acme.features.administrator.overture;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.overtures.Overture;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorOvertureRepository extends AbstractRepository {

	@Query("select i from Overture i where i.id = ?1")
	Overture findOneById(int id);

	@Query("select i from Overture i where i.deadline >= now()")
	Collection<Overture> findOvertureActives();
}
