
package acme.entities.application;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.job.Job;
import acme.entities.roles.Worker;
import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{4}-[A-Z]{4}:[A-Z]{4}") //Min:5 Max:15 EEEE-JJJJ:WWWW
	@Length(min = 5, max = 15)
	private String				ref;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				creationMoment;

	@NotBlank
	@Pattern(regexp = "ACCEPTED|REJECTED|PENDING") //pending, accepted, rejected
	private String				status;

	@NotBlank
	private String				statement;

	@NotBlank
	private String				skill;

	@NotBlank
	private String				qualification;

	private boolean				finalMode;

	//Relationships
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job					job;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Worker				worker;


	@Transient
	public Boolean spam(final Spamlist sl) {

		String fullText = this.statement + " " + this.skill + " " + this.qualification;

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
