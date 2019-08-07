package com.example.domain;

import java.math.BigDecimal;
import java.util.List;

public class Cart {

	private Integer id;
	private List<CartItem> cartEntryList;
	private BigDecimal totalPrice;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<CartItem> getCartEntryList() {
		return cartEntryList;
	}
	public void setCartEntryList(List<CartItem> cartEntryList) {
		this.cartEntryList = cartEntryList;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
