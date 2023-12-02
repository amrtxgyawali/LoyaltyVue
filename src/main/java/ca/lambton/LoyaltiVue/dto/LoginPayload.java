package ca.lambton.LoyaltiVue.dto;

import lombok.Data;

@Data
public class LoginPayload {
	private long phoneNumber;
	private String password;

}
