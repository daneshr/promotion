package com.example.promotion.business.promotion;

import java.math.BigDecimal;
import java.util.Map;

import com.example.promotion.dto.OrderItem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TwoProductsPromotion extends Promotion{
	
	private final Character firstProduct;
	private final Character secondProduct;
	private final int price;

	@Override
	protected boolean match(Map<Character, Long> remainedItems) {

		return Long.compare(remainedItems.getOrDefault(firstProduct, 0l) , 1l) >= 0 
				&& Long.compare(remainedItems.getOrDefault(secondProduct, 0l) , 1l) >= 0 ;
	}

	@Override
	protected OrderItem applyMe(Map<Character, Long> remainedItems) {
		remainedItems.put(firstProduct, remainedItems.get(firstProduct) - 1);
		remainedItems.put(secondProduct, remainedItems.get(secondProduct) - 1);
		return new OrderItem(firstProduct + " & " + secondProduct  , new BigDecimal(price));
	}

}
