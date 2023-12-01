package ca.lambton.LoyaltiVue.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.lambton.LoyaltiVue.dto.UserDetailsDto;
import ca.lambton.LoyaltiVue.models.UserDetails;
import ca.lambton.LoyaltiVue.repository.UserDetailsRepository;
import ca.lambton.LoyaltiVue.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserDetailsRepository userRepo;
	
	@Override
	public UserDetailsDto verifyUser(long phnNumber, String password) {
		UserDetails userDetails = userRepo.findByPhoneNum(phnNumber);
		if(userDetails != null && userDetails.getPassword().equals(password)) {
			 return UserDetailsDto.builder()
					 .firstName(userDetails.getFirstName())
					 .lastName(userDetails.getLastName())
					 .phoneNum(userDetails.getPhoneNum())
					 .latestPoints(userDetails.getLatestPoints())
					 .role(userDetails.getRole())
					 .build();
		}
		return null;
	}

}
