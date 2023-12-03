package ca.lambton.LoyaltiVue.models;

import java.util.Date;

import lombok.Data;

@Data
public class TransactionsDTO {
    private String id;
    private long userPhoneNumber;
    private Date transactionDate;
    private long pointsTillDate;
    private double transactionAmount;
    private String offerApplied;
    private long redeemPoints;
    private long pointsEarned;
} 

