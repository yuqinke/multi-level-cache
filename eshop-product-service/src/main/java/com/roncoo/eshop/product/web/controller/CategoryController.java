package com.roncoo.eshop.product.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.eshop.product.model.Category;
import com.roncoo.eshop.product.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/add") 
	@ResponseBody
	public String add(Category category, String operationType) {
		try {
			categoryService.add(category, operationType);
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/update") 
	@ResponseBody
	public String update(Category category, String operationType) {
		try {
			categoryService.update(category, operationType); 
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
			categoryService.delete(id, operationType); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return "error";
		}
		return "success";
	}
	
	@RequestMapping("/findById") 
	@ResponseBody
	public Category findById(Long id){
		try {
			return categoryService.findById(id);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return new Category();
	}
	
}
