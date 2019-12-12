
package acme.entities.auditRecord;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.entities.job.Job;
import acme.entities.roles.Auditor;
import acme.entities.spamlist.Spamlist;
import acme.entities.spamlist.Spamword;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditRecord extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Attributes

	@NotBlank
	private String				title;

	@NotBlank
	@Pattern(regexp = "DRAFT|PUBLISHED")
	private String				status;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				creationMoment;

	@NotBlank
	private String				body;

	//Relationships

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job					job;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Auditor				auditor;


	@Transient
	public Boolean spam(final Spamlist sl) {

		String fullText = this.title + " " + this.body;

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
