package com.roncoo.eshop.price.service;

import com.roncoo.eshop.price.model.ProductPrice;

public interface ProductPriceService {
	
	public void add(ProductPrice productPrice);
	
	public void update(ProductPrice productPrice);
	
	public void delete(Long id);
	
	public ProductPrice findById(Long id);
	
	public ProductPrice findByProductId(Long id);
	
}
