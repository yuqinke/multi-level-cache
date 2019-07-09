package com.roncoo.eshop.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.roncoo.eshop.product.model.Brand;

@Mapper
public interface BrandMapper {
	
	@Insert("INSERT INTO brand(name,description) VALUES(#{name},#{description})")  
	public void add(Brand brand);
	
	@Update("UPDATE brand SET name=#{name},description=#{description} WHERE id=#{id}")  
	public void update(Brand brand);
	
	@Delete("DELETE FROM brand WHERE id=#{id}")  
	public void delete(Long id);
	
	@Select("SELECT * FROM brand WHERE id=#{id}")  
	public Brand findById(Long id);
	
	@Select("SELECT * FROM brand WHERE id in (${ids})")
	public List<Brand> findByIds(@Param("ids") String ids);
	
}
