package ca.lambton.LoyaltiVue.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("UserDetails")
public class UserDetails {
	@Id
	private String id;
	private long phoneNum;
	private String password;
	private String role;
	private String firstName;
	private String lastName;
	private String latestPoints;

}
