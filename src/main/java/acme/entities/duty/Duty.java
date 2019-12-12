
package acme.entities.duty;

import java.beans.Transient;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.descriptor.Descriptor;
import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Duty extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@NotBlank
	private String				description;

	@NotNull
	@Max(7)
	@Min(1)
	private Integer				daysToComplete;


	@Transient
	public String getPercent() {
		String result = "";

		switch (this.daysToComplete) {
		case 1:
			result = "15%";
			break;
		case 2:
			result = "30%";
			break;
		case 3:
			result = "40%";
			break;
		case 4:
			result = "60%";
			break;
		case 5:
			result = "70%";
			break;
		case 6:
			result = "85%";
			break;
		case 7:
			result = "100%";
			break;
		}

		return result;
	}


	//Relationships
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Descriptor descriptor;


	@Transient
	public Boolean spam(final Spamlist sl) {

		String fullText = this.description + " " + this.title;

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
