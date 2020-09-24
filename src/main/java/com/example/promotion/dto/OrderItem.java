package com.example.promotion.dto;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class OrderItem {
	private final String description;
	private final BigDecimal price;

	public OrderItem(String description, BigDecimal price) {
		this.price = price.setScale(2);
		this.description = description;
	}

}
