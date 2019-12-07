
package acme.features.administrator.announcements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcements;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorAnnouncementDeleteService implements AbstractDeleteService<Administrator, Announcements> {

	//Internal State
	@Autowired
	AdministratorAnnouncementRepository repository;


	//Service Interface

	@Override
	public boolean authorise(final Request<Announcements> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Announcements> request, final Announcements entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationdate");

	}

	@Override
	public void unbind(final Request<Announcements> request, final Announcements entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "text", "addinfo");

	}

	@Override
	public Announcements findOne(final Request<Announcements> request) {
		assert request != null;
		Announcements result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Announcements> request, final Announcements entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Announcements> request, final Announcements entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
