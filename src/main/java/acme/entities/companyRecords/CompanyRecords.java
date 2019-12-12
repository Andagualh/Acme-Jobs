
package acme.entities.companyRecords;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompanyRecords extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				name;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				CEOname;

	@NotBlank
	private String				description;

	@URL
	@NotBlank
	private String				web;

	@Pattern(regexp = "(\\+[1-9][0-9]{0,2}\\s)?(\\([1-9][0-9]{0,3}\\)\\s)?([0-9]{6,10})")
	private String				phone;

	@Email
	private String				email;

	private Boolean				incorporated;

	@Range(min = 0, max = 5)
	private Integer				stars;


	@Transient
	public Boolean spam(final Spamlist sl) {

		String fullText = this.name + " " + this.sector + " " + this.CEOname + " " + this.description;

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
