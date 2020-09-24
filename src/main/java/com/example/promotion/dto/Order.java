package com.example.promotion.dto;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.example.promotion.common.Constants;

import lombok.Getter;

@Getter
public class Order {
	private BigDecimal totalPrice =Constants.ZERO;
	private List<OrderItem> items = new LinkedList<OrderItem>();
	public void addItem(OrderItem item) {
		items.add(item);
		totalPrice = totalPrice.add(item.getPrice());
	}
}
