package com.example.promotion.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
	public Map<Character,BigDecimal> getAllProducts();

	Optional<BigDecimal> getPrice(Character productName);
}
