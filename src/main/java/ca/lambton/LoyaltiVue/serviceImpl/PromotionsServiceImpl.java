package ca.lambton.LoyaltiVue.serviceImpl;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.lambton.LoyaltiVue.dto.PromotionsDTO;
import ca.lambton.LoyaltiVue.models.Promotions;
import ca.lambton.LoyaltiVue.repository.PromotionsRepository;
import ca.lambton.LoyaltiVue.service.PromotionsService;

@Service
public class PromotionsServiceImpl implements PromotionsService {

    @Autowired
    private PromotionsRepository promotionsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PromotionsDTO> getAllPromotions() {
        List<Promotions> promotionsList = promotionsRepository.findAll();
        return mapPromotionsListToDTOs(promotionsList);
    }

    @Override
    public PromotionsDTO getPromotionByPromoName(String promoName) {
        Optional<Promotions> optionalPromotion = promotionsRepository.findByPromoName(promoName);
        return optionalPromotion.map(promotion -> modelMapper.map(promotion, PromotionsDTO.class)).orElse(null);
    }

    @Override
    public PromotionsDTO createPromotion(PromotionsDTO promotionsDTO) {
        Promotions newPromotion = modelMapper.map(promotionsDTO, Promotions.class);
        Promotions savedPromotion = promotionsRepository.save(newPromotion);
        return modelMapper.map(savedPromotion, PromotionsDTO.class);
    }

    @Override
    public PromotionsDTO updatePromotion(String id, PromotionsDTO promotionsDTO) {
        Optional<Promotions> optionalPromotion = promotionsRepository.findById(id);

        if (optionalPromotion.isPresent()) {
            Promotions existingPromotion = optionalPromotion.get();
            existingPromotion.setPromoName(promotionsDTO.getPromoName());
            existingPromotion.setStartDate(promotionsDTO.getStartDate());
            existingPromotion.setEndDate(promotionsDTO.getEndDate());

            Promotions updatedPromotion = promotionsRepository.save(existingPromotion);
            return modelMapper.map(updatedPromotion, PromotionsDTO.class);
        } else {
            return null; // Handle the case where the promotion with the given ID is not found
        }
    }

    @Override
    public void deletePromotion(String id) {
        promotionsRepository.deleteById(id);
    }

    // Additional method to convert a list of Promotions to a list of PromotionsDTO
    private List<PromotionsDTO> mapPromotionsListToDTOs(List<Promotions> promotionsList) {
        return promotionsList.stream()
                .map(promotion -> modelMapper.map(promotion, PromotionsDTO.class))
                .collect(Collectors.toList());
    }

	@Override
	public Optional<List<Promotions>> getActivePromotion() {
		List<Promotions> promoList = promotionsRepository.findActivePromotions(new Date());
		return Optional.of(promoList);
	}

	
	@Override
	public boolean checkPromoName(String promoName) {
		Optional<Promotions> promotions = promotionsRepository.findByPromoName(promoName);
		return promotions.isPresent();
	}
}
