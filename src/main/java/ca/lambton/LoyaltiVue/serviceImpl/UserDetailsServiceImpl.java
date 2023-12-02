package ca.lambton.LoyaltiVue.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.lambton.LoyaltiVue.dto.RegisterPayload;
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

	@Override
	   public String registerUser(RegisterPayload register) {
       
        UserDetails newUser = new UserDetails();
        newUser.setPhoneNum(register.getPhoneNum());
        newUser.setPassword(register.getPassword());
        newUser.setRole(register.getRole());
        newUser.setFirstName(register.getFirstName());
        newUser.setLastName(register.getLastName());
        newUser.setLatestPoints(register.getLatestPoints());

        UserDetails savedUser = userRepo.save(newUser);

//        return UserDetailsDto.builder()
//                .firstName(savedUser.getFirstName())
//                .lastName(savedUser.getLastName())
//                .password(savedUser.getPassword())
//                .phoneNum(savedUser.getPhoneNum())
//                .latestPoints(savedUser.getLatestPoints())
//                .role(savedUser.getRole())
//                .build();
        return "Successfully Registered";
    }

	@Override
	public UserDetailsDto getUserDetails(long phnNumber) {
		UserDetails userDetails = userRepo.findByPhoneNum(phnNumber);
		if(userDetails != null) {
			 return UserDetailsDto.builder()
					 .firstName(userDetails.getFirstName())
					 .lastName(userDetails.getLastName())
					 .phoneNum(userDetails.getPhoneNum())
					 .latestPoints(userDetails.getLatestPoints())
					 .build();
		}
		return null;
	}
	

}
