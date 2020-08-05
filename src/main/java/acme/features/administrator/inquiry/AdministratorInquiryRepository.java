
package acme.features.administrator.inquiry;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.inquiries.Inquiry;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorInquiryRepository extends AbstractRepository {

	@Query("select i from Inquiry i where i.id = ?1")
	Inquiry findOneById(int id);

	@Query("select i from Inquiry i where i.deadline >= now()")
	Collection<Inquiry> findInquiryActives();
}
