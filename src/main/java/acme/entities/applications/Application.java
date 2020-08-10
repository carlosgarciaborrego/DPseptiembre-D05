
package acme.entities.applications;

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

import acme.entities.investmentRounds.InvestmentRound;
import acme.entities.roles.Investor;
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
public class Application extends DomainEntity {

	private static final long	serialVersionUID	= 4364563924515790536L;

	@NotBlank
	private String				ticker;

	private String				tickerOfInvest;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				creation;

	@NotBlank
	private String				statement;

	@NotNull
	@Valid
	private Money				offer;

	@NotBlank
	private String				status;

	private String				answer;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private InvestmentRound		investmentRound;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Investor			investor;

}
