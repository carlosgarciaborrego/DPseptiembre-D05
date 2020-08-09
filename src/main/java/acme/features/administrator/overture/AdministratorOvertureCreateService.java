
package acme.features.administrator.overture;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overtures.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorOvertureCreateService implements AbstractCreateService<Administrator, Overture> {

	@Autowired
	AdministratorOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation");
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "paragraphs", "minPrice", "maxPrice", "email");
	}

	@Override
	public Overture instantiate(final Request<Overture> request) {
		Overture result;

		result = new Overture();
		return result;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {

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

		errors.state(request, isFuture, "deadline", "administrator.inquiry.deadline");

		if (entity.getMaxPrice() != null && entity.getMinPrice() != null) {
			if (entity.getMaxPrice().getAmount() < entity.getMinPrice().getAmount()) {
				isOK = false;
			} else {
				isOK = true;
			}
		}

		errors.state(request, isOK, "maxPrice", "administrator.inquiry.maxPrice");

	}

	@Override
	public void create(final Request<Overture> request, final Overture entity) {
		Date creation;
		creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);
		this.repository.save(entity);
	}

}
