
package acme.entities.messageThread;

import java.beans.Transient;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.message.Message;
import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MessageThread extends DomainEntity {

	private static final long			serialVersionUID	= 1L;

	@NotBlank
	private String						title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date						creationMoment;

	@ManyToMany
	private Collection<Authenticated>	users;

	@NotNull
	@Valid
	@OneToMany(mappedBy = "thread")
	private Collection<Message>			message;


	@Transient
	public Boolean spam(final Spamlist sl) {

		String fullText = this.title;

		Collection<Spamword> spamwords = sl.getSpamwordslist();
		String[] splitedFullText = fullText.split(" ");

		Double numSpamWords = 0.;

		for (Spamword sw : spamwords) {
			for (String s : splitedFullText) {
				if (s.toLowerCase().equals(sw.getSpamword())) {
					numSpamWords++;
				}
			}
		}

		return numSpamWords / splitedFullText.length > sl.getThreshold();
	}

}
