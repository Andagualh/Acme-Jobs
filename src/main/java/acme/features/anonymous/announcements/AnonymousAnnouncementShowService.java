
package acme.features.anonymous.announcements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcements;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousAnnouncementShowService implements AbstractShowService<Anonymous, Announcements> {

	//Internal State

	@Autowired
	private AnonymousAnnouncementRepository repository;


	//AbstractShowService<Anonymous, Announcement> interface

	@Override
	public boolean authorise(final Request<Announcements> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Announcements> request, final Announcements entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationdate", "addinfo", "text");

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

}
