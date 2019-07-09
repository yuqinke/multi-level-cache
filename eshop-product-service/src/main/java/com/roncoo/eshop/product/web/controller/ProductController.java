package com.roncoo.eshop.product.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.eshop.product.model.Product;
import com.roncoo.eshop.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/add") 
	@ResponseBody
	public String add(Product product, String operationType) {
		try {
			productService.add(product, operationType);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/update") 
	@ResponseBody
	public String update(Product product, String operationType) {
		try {
			productService.update(product, operationType); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/delete") 
	@ResponseBody
	public String delete(Long id, String operationType) {
		try {
			productService.delete(id, operationType); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/findById") 
	@ResponseBody
	public Product findById(Long id){
		try {
			return productService.findById(id);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new Product();
	}
	
}
