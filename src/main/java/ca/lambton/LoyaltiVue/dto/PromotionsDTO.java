package ca.lambton.LoyaltiVue.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PromotionsDTO {
    private String id;
    private String promoName;
    private double promoPercentage; 
    private Date startDate;
    private Date endDate;
}

