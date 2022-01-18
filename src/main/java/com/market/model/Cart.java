package com.market.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<ProductsEnum> items;
    
    public Cart() {
	this.items = new ArrayList<>();	
    } 

    public void addItems(ProductsEnum item) {
	this.items.add(item);
    }

    public int getTotalItems() {
	return this.items.size();
    }

    @SuppressWarnings("unchecked")
	public List<ProductsEnum> getItems() {
	//This has been used to guarantee the model isolation
	//So the returned items won't be the original but a cloned one
	return new ArrayList<ProductsEnum>(items);
    }    
}
