package com.example.repository;

import java.math.BigDecimal;
import java.util.List;

import com.example.bean.CartInventoryBean;
import com.example.domain.CartItem;
import com.example.domain.Product;
import com.example.domain.User;

public interface CartItemDao {
	
    public List <CartItem> getCartItemList(User user);
    public void saveCartItem(CartItem cartItem);
    public CartItem getCartItem(int id);
    public CartItem getCartItemByProductAndUser(Product product, User user);
    public void deleteCartItem(int id);
	public void checkout(User user);
	public List<CartInventoryBean> generateCartInventoryBeanList(User user);
	public CartInventoryBean getCartInventoryBean(User user, int cartItemId);
	public void updateCartItemQuantity(CartItem cartItem, int quantity);
    public void addToCart(User user, Product product, int quantity);
    public BigDecimal generateSubTotal(int quantity, BigDecimal price);
    public BigDecimal generateTotalPrice(User user);
    public Boolean cartItemExist(Product product, User user);
	
}
