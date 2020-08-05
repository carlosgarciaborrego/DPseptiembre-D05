
package acme.entities.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Entrepreneur extends UserRole {

	private static final long	serialVersionUID	= 3548642848721257531L;

	@NotBlank
	private String				startUpName;

	@NotBlank
	private String				activitySector;

	@NotBlank
	private String				qualificationRecord;

	@NotBlank
	private String				skillRecord;

}
