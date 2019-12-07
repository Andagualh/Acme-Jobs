
package acme.entities.companyRecords;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

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

}
