
package acme.features.authenticated.notice;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.notices.Notice;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedNoticeRepository extends AbstractRepository {

	@Query("select n from Notice n where n.deadline >= now()")
	Collection<Notice> findNoticeActives();

	@Query("select n from Notice n where n.id = ?1")
	Notice findOneById(int id);
}
