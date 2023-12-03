package ca.lambton.LoyaltiVue.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import ca.lambton.LoyaltiVue.models.Promotions;

public interface PromotionsRepository extends MongoRepository<Promotions, String> {

    List<Promotions> findByStartDateBetweenAndEndDateBetween(Date start1, Date end1, Date start2, Date end2);
    
    Optional<Promotions> findByPromoName(String promoName);

    @Query("{'startDate': {$lte: ?0}, 'endDate': {$gte: ?0}}")
	List<Promotions> findActivePromotions(Date today);  

}
 