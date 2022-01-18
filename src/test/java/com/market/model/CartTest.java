package com.market.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class CartTest {
    Cart cart;

    @BeforeEach
    void setUp() {
	cart = new Cart();
    }

    @Test
    void addCartOneItem() {
	cart.addItems(ProductsEnum.CF1);

	assertEquals(1, cart.getTotalItems());
	    
    }  
    
    @Test
    void cartCannotBeModified() {
    	cart.addItems(ProductsEnum.CF1);
    	
        cart.getItems().add(ProductsEnum.SR1);
        
        assertEquals(1, cart.getTotalItems());
        assertEquals(ProductsEnum.CF1, cart.getItems().get(0));
    }    
}
