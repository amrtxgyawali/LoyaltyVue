package ca.lambton.LoyaltiVue.models;


import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Promotions")
public class Promotions {
	private String id;
	private String promoName;
	private double promoPercentage; 
	private Date startDate;
	private Date endDate;

}
