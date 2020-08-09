
package acme.entities.investmentRounds;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Entrepreneur;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "creation")
})
public class InvestmentRound extends DomainEntity {

	private static final long	serialVersionUID	= -7841105927115374574L;

	@NotBlank
	private String				ticker;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				creation;

	@NotBlank
	private String				kindRound;

	@NotBlank
	private String				title;

	@NotBlank
	private String				description;

	@NotNull
	@Valid
	private Money				amount;

	@URL
	private String				link;

	@NotNull
	private Boolean				active;

	@NotNull
	private Boolean				hasApp;

	@NotNull
	private Boolean				isInvestor;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Entrepreneur		entrepreneur;

}
