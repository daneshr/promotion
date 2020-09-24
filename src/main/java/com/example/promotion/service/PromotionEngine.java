package com.example.promotion.service;

import java.util.List;
import com.example.promotion.dto.Order;

public interface PromotionEngine {
	public Order prepareOrder(List<Character> items);
}
