
package acme.features.administrator.inquiry;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiries.Inquiry;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorInquiryUpdateService implements AbstractUpdateService<Administrator, Inquiry> {

	@Autowired
	AdministratorInquiryRepository repository;


	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation");
	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "paragraphs", "minPrice", "maxPrice", "email");
	}

	@Override
	public Inquiry findOne(final Request<Inquiry> request) {
		assert request != null;

		Inquiry result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isFuture = false;
		boolean isOK = false;

		if (entity.getDeadline() != null) {
			if (entity.getDeadline().before(new Date())) {
				isFuture = false;
			} else {
				isFuture = true;
			}
		}

		errors.state(request, isFuture, "deadline", "Deadline must be a date in future ");

		if (entity.getMaxPrice() != null && entity.getMinPrice() != null) {
			if (entity.getMaxPrice().getAmount() < entity.getMinPrice().getAmount()) {
				isOK = false;
			} else {
				isOK = true;
			}
		}
		errors.state(request, isOK, "maxPrice", "MaxPrice must be higher or equal that  minPrice");

	}

	@Override
	public void update(final Request<Inquiry> request, final Inquiry entity) {

		assert request != null;
		assert entity != null;

		Date creation;

		creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);
		this.repository.save(entity);
	}

}
