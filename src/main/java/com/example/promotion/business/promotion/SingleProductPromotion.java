package com.example.promotion.business.promotion;

import java.math.BigDecimal;
import java.util.Map;

import com.example.promotion.dto.OrderItem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SingleProductPromotion extends Promotion {

	private final Character name;
	private final int quantity;
	private final int price;

	@Override
	protected boolean match(Map<Character, Long> remainedItems) {

		return Long.compare(remainedItems.getOrDefault(name, 0l) , this.quantity) >=0;
	}

	@Override
	protected OrderItem applyMe(Map<Character, Long> remainedItems) {
		remainedItems.put(name, remainedItems.get(name) - quantity);
		return new OrderItem(quantity + " of " + name + "\'s", new BigDecimal(price));
	}

}
