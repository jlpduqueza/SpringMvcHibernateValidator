package com.example.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.bean.CartInventoryBean;
import com.example.domain.CartItem;
import com.example.domain.InventoryItem;
import com.example.domain.Product;
import com.example.domain.User;

@Repository
public class CartItemDaoImpl implements CartItemDao {
	
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private InventoryItemDao inventoryItemDao;
    
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CartItem> getCartItemList(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from CartItem where user_id = :id", CartItem.class);
		query.setParameter("id", user.getId());
		return query.getResultList();
	}

	@Override
	public void saveCartItem(CartItem cartItem) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(cartItem);
	}

	@Override
	public CartItem getCartItem(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(CartItem.class, id);
	}

	@Override
	public void deleteCartItem(int id) {
        Session session = sessionFactory.getCurrentSession();
        CartItem cartItem = session.byId(CartItem.class).load(id);
        session.delete(cartItem);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public void checkout(User user) {
        Session session = sessionFactory.getCurrentSession();
        List<CartItem> cartItemList = getCartItemList(user);
        
        for(CartItem cartItem : cartItemList) {
        	Integer id = cartItem.getProduct().getId();
        	InventoryItem inventoryItem = inventoryItemDao.getInventoryItemByProductId(id);
        	inventoryItem.setQuantity(inventoryItem.getQuantity() - cartItem.getQuantity());
        	inventoryItemDao.saveInventoryItem(inventoryItem);
        }
        
        Query query = session.createQuery("delete from CartItem where user_id = :userId");
        query.setParameter("userId", user.getId());
	}

	@Override
	public List<CartInventoryBean> generateCartInventoryBeanList(User user) {
		
		List<CartInventoryBean> cartInventoryBeanList = new ArrayList<>();
		List<CartItem> cartItemList = getCartItemList(user);
		
		for(CartItem cartItem : cartItemList) {
			CartInventoryBean cartInventoryBean = new CartInventoryBean();
			InventoryItem inventoryItem;
			Integer id = cartItem.getProduct().getId();
			inventoryItem = inventoryItemDao.getInventoryItemByProductId(id);
			
			cartInventoryBean.setCartItem(cartItem);
			cartInventoryBean.setStock(inventoryItem.getQuantity());
			cartInventoryBeanList.add(cartInventoryBean);
		}
		
		return cartInventoryBeanList;
	}
	
	@Override
	public CartInventoryBean getCartInventoryBean(User user, int cartItemId) {
		
		CartInventoryBean cartInventoryBean = null;
		
		List<CartInventoryBean> cartInventoryList = generateCartInventoryBeanList(user);
		for(CartInventoryBean cartBean : cartInventoryList) {
			if(cartBean.getCartItem().getId() == cartItemId) {
				cartInventoryBean = cartBean;
				return cartInventoryBean;
			}
		}
		return cartInventoryBean;
	}

	@Override
	public void addToCart(User user, Product product, int quantity) {
		CartItem cartItem = new CartItem();
		cartItem.setUser(user);
		cartItem.setProduct(product);
		cartItem.setQuantity(quantity);
		cartItem.setSubTotalPrice(generateSubTotal(quantity, product.getPrice()));
		
        if(cartItemExist(product, user)) {
        	CartItem actualCartItem = getCartItemByProductAndUser(product, user);
        	actualCartItem.setQuantity(cartItem.getQuantity()+actualCartItem.getQuantity());
    		saveCartItem(actualCartItem);
    		return;
        }
		saveCartItem(cartItem);
	}

	@Override
	public BigDecimal generateSubTotal(int quantity, BigDecimal price) {
		return price.multiply(new BigDecimal(quantity));
	}

	@Override
	public BigDecimal generateTotalPrice(User user) {
		BigDecimal totalPrice = new BigDecimal(0);
		
		for(CartItem cartItem : getCartItemList(user)) {
			totalPrice = totalPrice.add(cartItem.getSubTotalPrice());
		}
		
		return totalPrice;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Boolean cartItemExist(Product product, User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		try {
			
			Query query = currentSession.createQuery("from CartItem where user_id = :userId AND product_id = :productId", CartItem.class);
			query.setParameter("userId", user.getId());
			query.setParameter("productId", product.getId());
			
			System.out.println("user"+user.getId());
			
			if(query.getSingleResult() != null) {
				return true;
			}
		} catch (NoResultException e) {
		    System.out.println("null exception");
		}

		return false;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public CartItem getCartItemByProductAndUser(Product product, User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from CartItem where user_id = :userId AND product_id = :productId", CartItem.class);
		query.setParameter("userId", user.getId());
		query.setParameter("productId", product.getId());
		
		try {
			return (CartItem) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("null exception");
		}
		
		return null;
	}

	@Override
	public void updateCartItemQuantity(CartItem cartItem, int quantity) {
		cartItem.setQuantity(quantity);
		saveCartItem(cartItem);
	}


}
