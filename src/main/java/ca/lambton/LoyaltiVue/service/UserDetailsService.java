package ca.lambton.LoyaltiVue.service;

import ca.lambton.LoyaltiVue.dto.UserDetailsDto;

public interface UserDetailsService {
	
	public UserDetailsDto verifyUser(long phnNumber, String password);

}
