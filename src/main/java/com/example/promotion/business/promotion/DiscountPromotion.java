package com.example.promotion.business.promotion;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.promotion.dto.OrderItem;
import com.example.promotion.service.ProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DiscountPromotion extends Promotion {

	@Autowired
	ProductService productService;

	private final Character product;
	private final int newRate;

	@Override
	protected boolean match(Map<Character, Long> remainedItems) {
		return Long.compare(remainedItems.getOrDefault(product, 0l), 1l) >= 0;
	}

	@Override
	protected OrderItem applyMe(Map<Character, Long> remainedItems) {
		BigDecimal price = productService.getPrice(product)
				.orElseThrow(() -> new RuntimeException("Product was not found"));
		price = price.multiply(new BigDecimal(newRate / 100));
		remainedItems.put(product, remainedItems.get(product) - 1);
		return new OrderItem("new price for " + product + " is " + newRate + "%", price);
	}

}
