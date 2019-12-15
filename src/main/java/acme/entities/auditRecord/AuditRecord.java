
package acme.entities.auditRecord;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.entities.job.Job;
import acme.entities.roles.Auditor;
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

	@ManyToOne(optional = false)
	private Job					job;

	@ManyToOne(optional = false)
	private Auditor				auditor;


	@Transient
	public boolean finalMode() {

		return this.status.equals("PUBLISHED");
	}

}
