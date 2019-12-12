
package acme.entities.investorRecord;

import java.beans.Transient;
import java.util.Collection;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InvestorRecord extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	//Attributes----------------------------------
	@NotBlank
	private String				name;

	@NotBlank

	private String				sector;

	@NotBlank
	private String				statement;

	@NotNull
	@Range(min = 0, max = 5)
	private Integer				stars;


	@Transient
	public Boolean spam(final Spamlist sl) {

		String fullText = this.name + " " + this.sector + " " + this.statement;

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
