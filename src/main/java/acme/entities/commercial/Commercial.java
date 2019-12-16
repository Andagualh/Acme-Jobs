
package acme.entities.commercial;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Commercial extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@URL
	private String				banner;

	@NotBlank
	private String				slogan;

	@NotBlank
	@URL
	private String				url;

	@Valid
	@ManyToOne(optional = true)
	private CreditCard			card;

	@Valid
	@ManyToOne(optional = true)
	private Sponsor				sponsor;

}
