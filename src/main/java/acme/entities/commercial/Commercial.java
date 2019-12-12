
package acme.entities.commercial;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Sponsor;
import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Commercial extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@URL
	private String				banner;

	@NotBlank
	private String				slogan;

	@NotBlank
	@URL
	private String				url;

	@NotBlank
	private String				ownerName;

	@NotBlank
	private String				expireDate;

	@NotNull
	@Column(unique = true)
	private Integer				ccv;

	@NotBlank
	@CreditCardNumber
	private String				card;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Sponsor				sponsor;


	@Transient
	public Boolean spam(final Spamlist sl) {

		String fullText = this.slogan + " " + this.ownerName;

		Collection<Spamword> spamwords = sl.getSpamwordslist();

		Double numSpamWords = 0.;

		for (Spamword sw : spamwords) {
			String spamword = sw.getSpamword();
			numSpamWords = numSpamWords + this.numDeSpamwords(fullText, spamword, 0.);
		}

		return numSpamWords / 100 > sl.getThreshold();
	}

	private Double numDeSpamwords(final String fullText, final String spamword, final Double u) {
		if (!fullText.contains(spamword)) {
			return u;
		} else {
			Integer a = fullText.indexOf(spamword);
			return this.numDeSpamwords(fullText.substring(a + 1), spamword, u + 1);
		}
	}

}
