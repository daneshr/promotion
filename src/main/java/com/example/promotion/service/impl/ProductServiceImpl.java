package com.example.promotion.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.promotion.common.Constants;
import com.example.promotion.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private Map<Character, BigDecimal> products;

	@PostConstruct
	private void init() {
		products = new HashMap<Character, BigDecimal>();
		products.put('A', new BigDecimal(50));
		products.put('B', new BigDecimal(30));
		products.put('C', new BigDecimal(20));
		products.put('D', new BigDecimal(15));

	}

	@Override
	public Map<Character, BigDecimal> getAllProducts() {
		// TODO Auto-generated method stub
		return products;
	}

	@Override
	public Optional<BigDecimal> getPrice(Character productName) {
		// TODO Auto-generated method stub
		BigDecimal price =products.getOrDefault(productName,Constants.ZERO);
		Optional<BigDecimal> result =  price.compareTo(Constants.ZERO) >0 ? Optional.of(price) : Optional.empty();
		return result;
	}

}
