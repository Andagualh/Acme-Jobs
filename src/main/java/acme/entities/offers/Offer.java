
package acme.entities.offers;

import java.beans.Transient;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(indexes = {
	@Index(columnList = "deadline"), @Index(columnList = "ticker")
})
public class Offer extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationmoment;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				deadline;

	@NotBlank
	private String				description;

	@NotNull
	@Valid
	private Money				reward;

	@Column(unique = true)
	@Pattern(regexp = "O[A-Z]{4}\\-[0-9]{5}")
	private String				ticker;


	@Transient
	public Boolean spam(final Spamlist sl) {

		String fullText = this.title + " " + this.description;

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
