package ca.lambton.LoyaltiVue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.lambton.LoyaltiVue.dto.PromotionsDTO;
import ca.lambton.LoyaltiVue.service.PromotionsService;

@RestController
@RequestMapping("/api/promotions")
public class PromotionsController {

    @Autowired
    private PromotionsService promotionsService;

    @GetMapping
    public ResponseEntity<?> getAllPromotions() {
    	try {
        List<PromotionsDTO> promotions = promotionsService.getAllPromotions();
        if(promotions != null)
        	return ResponseEntity.ok(promotions);
        else
        	throw new Exception("Error reading transactions.");
    	} catch(Exception ex) {
    		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    	}
    }

    @GetMapping("/{name}")
    public ResponseEntity<?>  getPromotionById(@PathVariable String name) {
    	try {
        PromotionsDTO promotion = promotionsService.getPromotionByPromoName(name);
        if(promotion != null)
        	return ResponseEntity.ok(promotion);
        else
        	throw new Exception("No promotion found");
    	} catch(Exception e) {
    		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    	}
    }

    @PostMapping
    public ResponseEntity<?>  createPromotion(@RequestBody PromotionsDTO promotionsDTO) {
    	try {
    	if(promotionsService.checkPromoName(promotionsDTO.getPromoName())) {
    		return new ResponseEntity<String>("PromoCode already exists with same name. Please keep another name", HttpStatus.OK);
    	}
    	PromotionsDTO promotion = promotionsService.createPromotion(promotionsDTO);
    	if(promotion != null)
        	return ResponseEntity.ok(promotion);
        else
        	throw new Exception("Error creating new promotion");
    	} catch(Exception e) {
    		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    	}
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  updatePromotion(@PathVariable String id, @RequestBody PromotionsDTO promotionsDTO) {
    	try {    	
    	PromotionsDTO promotion = promotionsService.updatePromotion(id, promotionsDTO);
    	if(promotion != null)
        	return ResponseEntity.ok(promotion);
        else
        	throw new Exception("Error updating existing promotion");
    	} catch(Exception e) {
    		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    	}
    }

    @DeleteMapping("/{id}")
    public void deletePromotion(@PathVariable String id) {
        promotionsService.deletePromotion(id);
    }
}

