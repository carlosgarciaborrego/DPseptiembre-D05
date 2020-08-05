
package acme.features.administrator.toolRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.toolRecords.ToolRecord;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorToolRecordRepository extends AbstractRepository {

	@Query("select i from ToolRecord i where i.id = ?1")
	ToolRecord findOneById(int id);

	@Query("select i from ToolRecord i")
	Collection<ToolRecord> findAllToolRecord();
}
