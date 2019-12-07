
package acme.features.administrator.announcements;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcements;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorAnnouncementListService implements AbstractListService<Administrator, Announcements> {

	//Internal State

	@Autowired
	AdministratorAnnouncementRepository repository;


	//AbstractListService<Administrator, Announcement> interface

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

		request.unbind(entity, model, "creationdate", "title");

	}

	@Override
	public Collection<Announcements> findMany(final Request<Announcements> request) {
		assert request != null;
		Collection<Announcements> announlist;

		announlist = this.repository.findManyAll();

		return announlist;
	}

}
