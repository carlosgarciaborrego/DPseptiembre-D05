
package acme.features.administrator.notice;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.notices.Notice;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorNoticeRepository extends AbstractRepository {

	@Query("select n from Notice n")
	Collection<Notice> findAllNotice();

	@Query("select n from Notice n where n.id = ?1")
	Notice findOneById(int id);
}
