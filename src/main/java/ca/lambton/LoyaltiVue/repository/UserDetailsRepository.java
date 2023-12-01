package ca.lambton.LoyaltiVue.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import ca.lambton.LoyaltiVue.models.UserDetails;

public interface UserDetailsRepository extends MongoRepository<UserDetails, String>{
	
	@Query("{phoneNum: '?0'}")
	UserDetails findByPhoneNum(long phoneNumber);

}
