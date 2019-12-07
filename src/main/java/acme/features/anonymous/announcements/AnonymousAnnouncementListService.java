
package acme.features.anonymous.announcements;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcements;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousAnnouncementListService implements AbstractListService<Anonymous, Announcements> {

	//Internal State

	@Autowired
	AnonymousAnnouncementRepository repository;


	//AbstractListService<Anonymous, Announcement> interface

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

		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		Date m = new Date(c.getTimeInMillis());
		announlist = this.repository.findManyAll(m);

		return announlist;
	}

}
