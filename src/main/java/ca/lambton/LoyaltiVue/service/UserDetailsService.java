package ca.lambton.LoyaltiVue.service;

import ca.lambton.LoyaltiVue.dto.RegisterPayload;
import ca.lambton.LoyaltiVue.dto.UserDetailsDto;

public interface UserDetailsService {
	
	public UserDetailsDto verifyUser(long phnNumber, String password);
	public String registerUser(RegisterPayload register);
	public UserDetailsDto getUserDetails(long phnNumber);


	
	

}
