
package acme.entities.spamlist;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "spamword")
})
public class Spamword extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				spamword;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	Spamlist					spamlist;

}
