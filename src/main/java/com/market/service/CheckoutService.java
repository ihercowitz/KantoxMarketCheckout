package com.market.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.stream.Collectors;

import com.market.model.Cart;
import com.market.model.ProductsEnum;

public class CheckoutService {

	DecimalFormat format = new DecimalFormat("0.00");
	
	private String currency = "£";
	
	public String calculate(Cart cart) {
		
		Map<String, Long> groupedItems = cart.getItems().stream()
				.collect(Collectors.groupingBy(ProductsEnum::name, 
						 Collectors.counting()));
		
		Double total = groupedItems.entrySet()
		.stream()
		.map(item -> ProductsEnum.valueOf(item.getKey())
				                 .validation(item.getValue()))
		.reduce(0.0, Double::sum);
		
		return currency
				.concat(BigDecimal.valueOf(total)
				.setScale(2, RoundingMode.HALF_UP)
				.toString());
	}
	
}
