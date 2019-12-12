
package acme.entities.descriptor;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.duty.Duty;
import acme.entities.job.Job;
import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Descriptor extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	//Atributes----------------------------------------------
	@NotNull
	@NotBlank
	private String				description;

	@NotNull
	@Valid
	@OneToMany(mappedBy = "descriptor")
	private Collection<Duty>	duty;

	//RelationShips
	@NotNull
	@Valid
	@OneToOne(optional = false)
	private Job					job;


	@Transient
	public Boolean spam(final Spamlist sl) {

		String fullText = this.description;

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
