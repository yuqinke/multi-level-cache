package com.roncoo.eshop.product.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.roncoo.eshop.product.model.Product;

@Mapper
public interface ProductMapper {
	
	@Insert("INSERT INTO product(name,category_id,brand_id) VALUES(#{name},#{categoryId},#{brandId})")  
	public void add(Product product);
	
	@Update("UPDATE product SET name=#{name},category_id=#{categoryId},brand_id=#{brandId} WHERE id=#{id}")  
	public void update(Product product);
	
	@Delete("DELETE FROM product WHERE id=#{id}")  
	public void delete(Long id);
	
	@Select("SELECT * FROM product WHERE id=#{id}")  
	@Results({
		@Result(column = "category_id", property = "categoryId"),
		@Result(column = "brand_id", property = "brandId")  
	})
	public Product findById(Long id);
	
}
