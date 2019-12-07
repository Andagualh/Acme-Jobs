
package acme.entities.duty;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.descriptor.Descriptor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Duty extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				title;

	@NotBlank
	private String				description;

	@NotNull
	@Max(7)
	@Min(1)
	private Integer				daysToComplete;


	@Transient
	public String getPercent() {
		String result = "";

		switch (this.daysToComplete) {
		case 1:
			result = "15%";
			break;
		case 2:
			result = "30%";
			break;
		case 3:
			result = "40%";
			break;
		case 4:
			result = "60%";
			break;
		case 5:
			result = "70%";
			break;
		case 6:
			result = "85%";
			break;
		case 7:
			result = "100%";
			break;
		}

		return result;
	}


	//Relationships
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Descriptor descriptor;

}
