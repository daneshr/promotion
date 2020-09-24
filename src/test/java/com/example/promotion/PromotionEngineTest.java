package com.example.promotion;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.promotion.dto.Order;
import com.example.promotion.service.impl.PromotionEngineImpl;

@SpringBootTest
public class PromotionEngineTest {

	@Autowired
	private PromotionEngineImpl promotionEngine;

	@Test
	public void shouldThrowIllegalArgumentException_whenReceiveUnsupportedProduct() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			promotionEngine.prepareOrder(Arrays.asList('Z'));
		});
	}

	@Test
	public void shouldThrowIllegalArgumentException_whenReceiveUnsupportedCart() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			promotionEngine.prepareOrder(Arrays.asList('A', 'Z'));
		});
	}

	@Test
	public void shouldReturnProperOrder_whenReceiveScenario1() {
		Order order = promotionEngine.prepareOrder(Arrays.asList('A', 'B', 'C'));
		Assertions.assertTrue((new BigDecimal(100)).compareTo(order.getTotalPrice()) == 0);
		Assertions.assertIterableEquals(
				order.getItems().stream().map((item) -> item.getDescription()).collect(Collectors.toList()),
				Arrays.asList("1 * A", "1 * B", "1 * C"));
	}

	@Test
	public void shouldReturnProperOrder_whenReceiveScenario2() {
		Order order = promotionEngine
				.prepareOrder(Arrays.asList('A', 'B', 'A', 'B', 'A', 'B', 'A', 'B', 'A', 'B', 'C'));
		Assertions.assertTrue((new BigDecimal(370)).compareTo(order.getTotalPrice()) == 0);
		Assertions.assertIterableEquals(
				order.getItems().stream().map((item) -> item.getDescription()).collect(Collectors.toList()),
				Arrays.asList("3 of A\'s", "2 of B\'s", "2 of B\'s", "2 * A", "1 * B", "1 * C"));
	}

	@Test
	public void shouldReturnProperOrder_whenReceiveScenario3() {
		Order order = promotionEngine.prepareOrder(Arrays.asList('B', 'A', 'B', 'B', 'A', 'B', 'A', 'B', 'C', 'D'));
		Assertions.assertTrue((new BigDecimal(280)).compareTo(order.getTotalPrice()) == 0);
		Assertions.assertIterableEquals(
				order.getItems().stream().map((item) -> item.getDescription()).collect(Collectors.toList()),
				Arrays.asList("3 of A\'s", "2 of B\'s", "2 of B\'s", "C & D", "1 * B"));
	}

}
