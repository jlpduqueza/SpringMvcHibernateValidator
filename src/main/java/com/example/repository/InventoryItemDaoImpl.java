package com.example.repository;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.InventoryItem;

@Repository
public class InventoryItemDaoImpl implements InventoryItemDao {

    @Autowired
    private SessionFactory sessionFactory;
    
	@Override
	public List<InventoryItem> getInventoryList() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.createQuery("from InventoryItem", InventoryItem.class).getResultList();
	}
	
	@Override
	public List<InventoryItem> getInventoryListForUser() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.createQuery("from InventoryItem where quantity > 0", InventoryItem.class).getResultList();
	}

	@Override
	public void saveInventoryItem(InventoryItem inventoryItem) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(inventoryItem);
	}

	@Override
	public InventoryItem getInventoryItem(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(InventoryItem.class, id);
	}

	@Override
	public InventoryItem getInventoryItemByProductId(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("From InventoryItem where product_id = :id");
		query.setParameter("id", id);
		return (InventoryItem) query.getSingleResult();
	}
	
	@Override
	public void deleteInventoryItem(int id) {
        Session session = sessionFactory.getCurrentSession();
        InventoryItem inventoryItem = session.byId(InventoryItem.class).load(id);
        session.delete(inventoryItem);
	}

	@Override
	public void updateInventoryItemQuantity(int id, int quantity) {
        InventoryItem inventoryItem = getInventoryItem(id);
        inventoryItem.setQuantity(quantity);
        saveInventoryItem(inventoryItem);
	}

}
