
package acme.entities.creditCard;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import acme.entities.commercial.Commercial;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditCard extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				ownerName;

	@NotBlank
	@CreditCardNumber
	private String				number;

	@Pattern(regexp = "[0-9]{2}/[0-9]{4}")
	@NotNull
	private String				deadline;

	@NotBlank
	@Min(3)
	@Max(3)
	private String				cvv;

	@Valid
	@OneToOne(optional = true)
	private Commercial			commercial;

}
