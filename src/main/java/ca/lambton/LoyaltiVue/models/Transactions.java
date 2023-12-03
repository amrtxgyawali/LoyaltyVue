package ca.lambton.LoyaltiVue.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Transactions")
public class Transactions {

	@Id
	private String id;
	private long userPhoneNumber;
	private Date transactionDate;
	private long pointsTillDate;
	private double transactionAmount;
	private String offerApplied;
	private double redeemPoints;
	private long pointsEarned;
	private String remarks;
}
