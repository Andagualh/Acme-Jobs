
package acme.entities.job;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.application.Application;
import acme.entities.auditRecord.AuditRecord;
import acme.entities.descriptor.Descriptor;
import acme.entities.roles.Employer;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job extends DomainEntity {

	/**
	 *
	 */
	private static final long		serialVersionUID	= 1L;

	//Atributes

	@Column(unique = true)
	@NotBlank
	@Length(min = 5, max = 10)
	@Pattern(regexp = "[A-Z\\d]{4}-[A-Z\\d]{4}")
	private String					reference;

	@NotBlank
	@Pattern(regexp = "DRAFT|PUBLISHED")
	private String					status;

	@NotBlank
	private String					title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date					deadline;

	@NotNull
	@Valid
	private Money					salary;

	@NotBlank
	private String					description;

	@URL
	private String					moreInfo;

	@OneToOne(optional = true)
	private Descriptor				descriptor;

	private boolean					finalMode;

	//RelationShips
	@ManyToOne(optional = false)
	private Employer				employer;

	@OneToMany(mappedBy = "job")
	private Collection<Application>	application;

	@OneToMany(mappedBy = "job")
	private Collection<AuditRecord>	auditRecord;
}
