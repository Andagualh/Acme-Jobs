
package acme.entities.messageThread;

import javax.persistence.Entity;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MessageThreadAuthenticated extends DomainEntity {

	private static final long serialVersionUID = 1L;

}
