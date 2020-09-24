package com.example.promotion.business;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.promotion.dto.OrderItem;
import com.example.promotion.service.ProductService;

@Service
public class OrdinaryBuyer {
	
	private ProductService productService;
	
	@Autowired
	public OrdinaryBuyer(ProductService productService) {
		this.productService = productService;
	}

	public List<OrderItem> buyAll(Map<Character,Long> remainedItems) {
		List<OrderItem> result = new LinkedList<OrderItem>();
		for (Map.Entry<Character, Long> entry: remainedItems.entrySet()) {
			BigDecimal price = productService.getPrice(entry.getKey()).orElseThrow(()->  new IllegalArgumentException("Product doesn't exist!"));
			OrderItem item = new OrderItem(entry.getValue() + " * "+ entry.getKey(),price.multiply(new BigDecimal(entry.getValue())));
			result.add(item);
			
		}		
		return result;
	
	}

	
}
