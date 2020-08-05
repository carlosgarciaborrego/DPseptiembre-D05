
package acme.features.administrator.notice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notices.Notice;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNoticeCreateService implements AbstractCreateService<Administrator, Notice> {

	@Autowired
	AdministratorNoticeRepository repository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creation");
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "title", "deadline", "body", "active", "links", "active");
	}

	@Override
	public Notice instantiate(final Request<Notice> request) {
		Notice result;

		result = new Notice();
		return result;
	}

	@Override
	public void validate(final Request<Notice> request, final Notice entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isFuture = false;
		boolean isActive = false;

		if (entity.getDeadline() != null) {
			if (entity.getDeadline().before(new Date())) {
				isFuture = false;
			} else {
				isFuture = true;
			}
		}

		errors.state(request, isFuture, "deadline", "Deadline must be a date in future ");

		if (entity.getActive() != true) {
			isActive = false;
		} else {
			isActive = true;
		}
		errors.state(request, isActive, "active", "Active must be true");

	}

	@Override
	public void create(final Request<Notice> request, final Notice entity) {
		Date creation;
		creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);
		this.repository.save(entity);
	}

}
