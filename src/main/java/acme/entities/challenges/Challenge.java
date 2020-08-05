
package acme.entities.challenges;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "deadline")
})
public class Challenge extends DomainEntity {

	private static final long	serialVersionUID	= 1444330452368091750L;

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	private String				description;

	@NotBlank
	private String				goalRookie;

	@NotNull
	@Valid
	private Money				rewardRookie;

	@NotBlank
	private String				goalAverage;

	@NotNull
	@Valid
	private Money				rewardAverage;

	@NotBlank
	private String				goalExpert;

	@NotNull
	@Valid
	private Money				rewardExpert;

}
