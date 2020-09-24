package com.example.promotion;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.promotion.dto.Order;


@SpringBootTest
public class PromotionEngineTest {

    @Autowired
    private com.example.promotion.service.PromotionEngine promotionEngine;

    @Test
    public void shouldThrowIllegalArgumentException_whenReceiveUnsupportedProduct() {
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    	    promotionEngine.prepareOrder(Arrays.asList('Z'));
    	  });
    }
    @Test
    public void shouldThrowIllegalArgumentException_whenReceiveUnsupportedCart() {
    	Assertions.assertThrows(IllegalArgumentException.class, () -> {
    	    promotionEngine.prepareOrder(Arrays.asList('A','Z'));
    	  });
    }
    @Test
    public void shouldReturnProperOrder_whenReceiveScenario1() {
    	Order order = promotionEngine.prepareOrder(Arrays.asList('A','B','C'));
    	Assertions.assertTrue((new BigDecimal(100)).compareTo(order.getTotalPrice())==0);
    	Assertions.assertIterableEquals(
    			order.getItems().stream().map((item)->item.getDescription()).collect(Collectors.toList())
    			, Arrays.asList("1 * A","1 * B","1 * C"));
    }

 
}
