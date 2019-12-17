
package acme.entities.spamlist;

import java.beans.Transient;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spamlist extends DomainEntity {

	private static final long		serialVersionUID	= 1L;

	@NotBlank
	private String					idiom;

	@NotNull
	@Valid
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "spamlist")
	private Collection<Spamword>	spamwordslist;


	@Transient
	public Double getThreshold() {
		Double result;
		result = 0.01;
		/*
		 * while (result < this.spamwordslist.size()) {
		 * result++;
		 * }
		 */

		//return result / 100;
		return result;
	}

}
