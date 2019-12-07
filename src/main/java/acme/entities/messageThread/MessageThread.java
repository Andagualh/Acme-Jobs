
package acme.entities.messageThread;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.message.Message;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MessageThread extends DomainEntity {

	private static final long			serialVersionUID	= 1L;

	@NotBlank
	private String						title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date						creationMoment;

	@ManyToMany
	private Collection<Authenticated>	users;

	@NotNull
	@Valid
	@OneToMany(mappedBy = "thread")
	private Collection<Message>			message;

}
