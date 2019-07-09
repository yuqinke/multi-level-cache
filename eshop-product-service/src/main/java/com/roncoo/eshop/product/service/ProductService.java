package com.roncoo.eshop.product.service;

import com.roncoo.eshop.product.model.Product;

public interface ProductService {
	
	public void add(Product product, String operationType);
	
	public void update(Product product, String operationType);
	
	public void delete(Long id, String operationType);
	
	public Product findById(Long id);
	
}
