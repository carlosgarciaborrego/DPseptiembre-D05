
package acme.features.administrator.technologyRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.technologyRecords.TechnologyRecord;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorTechnologyRecordRepository extends AbstractRepository {

	@Query("select i from TechnologyRecord i where i.id = ?1")
	TechnologyRecord findOneById(int id);

	@Query("select i from TechnologyRecord i")
	Collection<TechnologyRecord> findAllTechnologyRecord();
}
