package com.example.promotion.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.promotion.business.OrdinaryBuyer;
import com.example.promotion.dto.Order;
import com.example.promotion.service.PromotionEngine;
import com.example.promotion.service.PromotionService;

@Service
public class PromotionEngineImpl implements PromotionEngine {

	private OrdinaryBuyer buyer;
	private PromotionService promotionService;

	@Autowired
	PromotionEngineImpl(OrdinaryBuyer buyer, PromotionService promotionService) {
		this.buyer = buyer;
		this.promotionService = promotionService;
	}

	public Order prepareOrder(List<Character> items) {
		items = items.stream().map(Character::toUpperCase).collect(Collectors.toList());
		Map<Character, Long> remainedItems = items.stream()
				.collect(Collectors.groupingBy((ch) -> ch, Collectors.counting()));
		Order order = new Order();
		promotionService.getAllPromotions().stream().map((promotion) -> promotion.apply(remainedItems))
				.flatMap(Collection::stream).forEach((item) -> order.addItem(item));
		buyer.buyAll(remainedItems).forEach((item) -> order.addItem(item));

		return order;

	}

}
