package com.example.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.Product;

@Repository
public class ProductDaoImpl implements ProductDao{

    @Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public List<Product> getProductList() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.createQuery("from Product", Product.class).getResultList();
	}

	@Override
	public void saveProduct(Product product) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(product);
	}

	@Override
	public Product getProduct(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Product.class, id);
	}

	@Override
	public void deleteProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.byId(Product.class).load(id);
        session.delete(product);
	}

}
