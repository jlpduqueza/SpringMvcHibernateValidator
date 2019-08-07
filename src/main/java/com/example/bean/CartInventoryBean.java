package com.example.bean;

import com.example.domain.CartItem;

public class CartInventoryBean {

	private CartItem cartItem;
	private Integer stock;
	
	public CartItem getCartItem() {
		return cartItem;
	}

	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
}
