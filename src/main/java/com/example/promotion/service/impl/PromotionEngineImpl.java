package com.example.promotion.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.promotion.business.OrdinaryBuyer;
import com.example.promotion.dto.Order;
import com.example.promotion.service.PromotionEngine;

@Service
public class PromotionEngineImpl implements PromotionEngine{
	
	private OrdinaryBuyer buyer;
	
	@Autowired
	PromotionEngineImpl(OrdinaryBuyer buyer){
		this.buyer = buyer;
	}
	public Order prepareOrder(List<Character> items) {
		items= items.stream().map(Character::toUpperCase).collect(Collectors.toList());
		Map<Character,Long> remainedItems = items.stream().collect(Collectors.groupingBy((ch)->ch,Collectors.counting()));
		Order order = new Order();
		buyer.buyAll(remainedItems).forEach((item)->order.addItem(item));
		
		return order;
			
	}

}
