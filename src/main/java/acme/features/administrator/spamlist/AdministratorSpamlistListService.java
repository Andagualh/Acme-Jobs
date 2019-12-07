
package acme.features.administrator.spamlist;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorSpamlistListService implements AbstractListService<Administrator, Spamlist> {

	@Autowired
	AdministratorSpamlistRepository repository;


	@Override
	public boolean authorise(final Request<Spamlist> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Spamlist> request, final Spamlist entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		StringBuilder buffer;
		Collection<Spamword> spamwords;

		request.unbind(entity, model, "threshold");

		spamwords = this.repository.findManySpamwordsById(entity.getId());
		buffer = new StringBuilder();
		buffer.append("[");
		Integer x = spamwords.size();
		Integer i = 0;
		for (Spamword word : spamwords) {
			buffer.append(word.getSpamword());
			if (i < x - 1) {
				buffer.append(", ");
			}
			i++;
		}

		model.setAttribute("spamwordslist", buffer.toString() + "]");

	}

	@Override
	public Collection<Spamlist> findMany(final Request<Spamlist> request) {
		assert request != null;

		Collection<Spamlist> result;

		result = this.repository.findManyAll();

		return result;
	}

}
