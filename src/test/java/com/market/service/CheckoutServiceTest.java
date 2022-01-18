package com.market.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.market.model.Cart;
import com.market.model.ProductsEnum;

public class CheckoutServiceTest {

	CheckoutService checkoutService;
	
	@BeforeEach
	void setUp() {
		checkoutService = new CheckoutService();
	}
	
//	@Test
//	void sumarizeTotalItens() {
//		Cart cart = new Cart();
//		cart.addItems(ProductsEnum.CF1);
//		cart.addItems(ProductsEnum.GR1);
//		cart.addItems(ProductsEnum.SR1);
//		
//		Map<String, Long> expected = new HashMap<>();
//		expected.put(ProductsEnum.CF1.name(), 1L);
//		expected.put(ProductsEnum.GR1.name(), 1L);
//		expected.put(ProductsEnum.SR1.name(), 1L);
//		
//		assertEquals(expected, checkoutService.calculate(cart));
//	}
	
	@Test
	@DisplayName("Test Checkout Calculate Method")
	void calculatePriceSingleProducts() {
		Cart cart = new Cart();
		cart.addItems(ProductsEnum.CF1);
		cart.addItems(ProductsEnum.GR1);
		cart.addItems(ProductsEnum.SR1);
		
		assertEquals("£19.34", checkoutService.calculate(cart));
	}
	
	@Test
	@DisplayName("GR1 - Buy-One-Get-One Validation - Only 2 GR1 items")
	void calculateBuyOneGetOneGR1() {
		Cart cart = new Cart();
		cart.addItems(ProductsEnum.GR1);
		cart.addItems(ProductsEnum.GR1);
		
		assertEquals("£3.11", checkoutService.calculate(cart));
	}	
	
	@Test
	@DisplayName("GR1 - Buy-One-Get-One Validation - multiple itens")
	void calculateBuyOneGetOneGR1MultipleItens() {
		Cart cart = new Cart();
		cart.addItems(ProductsEnum.CF1);
		cart.addItems(ProductsEnum.GR1);
		cart.addItems(ProductsEnum.GR1);
		cart.addItems(ProductsEnum.SR1);
		cart.addItems(ProductsEnum.GR1);
		
		assertEquals("£22.45", checkoutService.calculate(cart));
	}
	
	@Test
	@DisplayName("SR1 - Strawberries get discount on bulk purchases 3 or more")
	void calculateStrawberryDiscount() {
		Cart cart = new Cart();
		cart.addItems(ProductsEnum.SR1);
		cart.addItems(ProductsEnum.SR1);
		cart.addItems(ProductsEnum.GR1);
		cart.addItems(ProductsEnum.SR1);
		
		assertEquals("£16.61", checkoutService.calculate(cart));
	}
	
	@Test
	@DisplayName("SR1 - Strawberries get no discount if not bulk purchases")
	void calculateStrawberryPriceNotBulk() {
		Cart cart = new Cart();
		cart.addItems(ProductsEnum.CF1);
		cart.addItems(ProductsEnum.SR1);
		cart.addItems(ProductsEnum.GR1);
		cart.addItems(ProductsEnum.SR1);

		assertEquals("£24.34", checkoutService.calculate(cart));
	}	
	
	@Test
	@DisplayName("CF1 - Buy 3 or more - price drops to 2/3")
	void calculateCoffeeDiscount() {
		Cart cart = new Cart();
		cart.addItems(ProductsEnum.CF1);
		cart.addItems(ProductsEnum.GR1);
		cart.addItems(ProductsEnum.SR1);
		cart.addItems(ProductsEnum.CF1);
		cart.addItems(ProductsEnum.CF1);

		assertEquals("£30.57", checkoutService.calculate(cart));
	}	
	
	@Test
	@DisplayName("CF1 - Buy 2 or less - no discount applied")
	void calculateCoffeeWithoutDiscount() {
		Cart cart = new Cart();
		cart.addItems(ProductsEnum.CF1);
		cart.addItems(ProductsEnum.GR1);
		cart.addItems(ProductsEnum.SR1);
		cart.addItems(ProductsEnum.CF1);

		assertEquals("£30.57", checkoutService.calculate(cart));
	}	

	@Test
	@DisplayName("Get the Discount on all the products")
	void calculateDiscountForAllProducts() {
		Cart cart = new Cart();
		cart.addItems(ProductsEnum.CF1);
		cart.addItems(ProductsEnum.GR1);
		cart.addItems(ProductsEnum.SR1);
		cart.addItems(ProductsEnum.SR1);
		cart.addItems(ProductsEnum.CF1);
		cart.addItems(ProductsEnum.GR1);
		cart.addItems(ProductsEnum.SR1);
		cart.addItems(ProductsEnum.CF1);
		cart.addItems(ProductsEnum.GR1);
		
		assertEquals("£42.18", checkoutService.calculate(cart));
	}	

}
