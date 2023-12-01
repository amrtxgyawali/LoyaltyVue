package ca.lambton.LoyaltiVue.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDto {

	private long phoneNum;
	private String password;
	private String role;
	private String firstName;
	private String lastName;
	private String latestPoints;
}
