package ca.lambton.LoyaltiVue.service;

import java.util.List;
import java.util.Optional;

import ca.lambton.LoyaltiVue.dto.PromotionsDTO;
import ca.lambton.LoyaltiVue.models.Promotions;

public interface PromotionsService {

    List<PromotionsDTO> getAllPromotions();

    PromotionsDTO createPromotion(PromotionsDTO promotionsDTO);

    PromotionsDTO updatePromotion(String id, PromotionsDTO promotionsDTO);

    void deletePromotion(String id);

	Optional<List<Promotions>> getActivePromotion();

	PromotionsDTO getPromotionByPromoName(String name);

	boolean checkPromoName(String promoName);
}
