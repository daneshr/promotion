package com.example.promotion.business.promotion;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.example.promotion.dto.OrderItem;

public abstract class Promotion {
	public final List<OrderItem> apply(Map<Character, Long> remainedItems) {
		List<OrderItem> result = new LinkedList<OrderItem>();
		while (match(remainedItems)) {
			result.add(applyMe(remainedItems));
		}
		return result;
	}

	protected abstract boolean match(Map<Character, Long> remainedItems);

	protected abstract OrderItem applyMe(Map<Character, Long> remainedItems);

}
