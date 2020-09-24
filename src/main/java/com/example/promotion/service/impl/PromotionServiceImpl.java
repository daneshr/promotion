package com.example.promotion.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.promotion.business.promotion.Promotion;
import com.example.promotion.business.promotion.SingleProductPromotion;
import com.example.promotion.business.promotion.TwoProductsPromotion;
import com.example.promotion.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	private List<Promotion> promotions;

	@PostConstruct
	private void init() {
		promotions = new LinkedList<Promotion>();
		promotions.add(new SingleProductPromotion('A', 3, 130));
		promotions.add(new SingleProductPromotion('B', 2, 45));
		promotions.add(new TwoProductsPromotion('C', 'D', 30));

	}

	@Override
	public List<Promotion> getAllPromotions() {
		return promotions;
	}
}
