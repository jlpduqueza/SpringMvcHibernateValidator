package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Product;
import com.example.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
	
	@Override
	public List<Product> getProductList() {
		return productDao.getProductList();
	}

	@Override
    @Transactional
	public void saveProduct(Product product) {
		productDao.saveProduct(product);
	}

	@Override
	public Product getProduct(int id) {
		return productDao.getProduct(id);
	}

	@Override
	public void deleteProduct(int id) {
		productDao.deleteProduct(id);
	}

}
