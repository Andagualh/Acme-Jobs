
package acme.entities.challenge;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Challenge extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				deadline;

	@NotBlank
	private String				description;

	@NotBlank
	private String				gold;

	@NotBlank
	private String				silver;

	@NotBlank
	private String				bronze;

	@NotNull
	@Valid
	private Money				goldreward;

	@NotNull
	@Valid
	private Money				silverreward;

	@NotNull
	@Valid
	private Money				bronzereward;


	@Transient
	public Boolean spam(final Spamlist sl) {

		String fullText = this.title + " " + this.gold + " " + this.silver + " " + this.bronze;

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
