package com.example.promotion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.promotion.dto.Order;

@Service
public class PromotionEngine {
	
	public Order prepareOrder(List<Character> items) {
		Order order = new Order();
		
		return order;
			
	}

}
