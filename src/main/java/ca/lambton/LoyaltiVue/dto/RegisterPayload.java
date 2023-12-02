package ca.lambton.LoyaltiVue.dto;

import lombok.Data;


@Data
public class RegisterPayload {
	
	private long phoneNum;
	private String password;
	private String role;
	private String firstName;
	private String lastName;
	private String latestPoints;


}
