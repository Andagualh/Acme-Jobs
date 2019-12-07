
package acme.entities.investorRecord;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

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

}
