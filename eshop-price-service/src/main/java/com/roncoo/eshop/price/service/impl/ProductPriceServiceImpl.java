package com.roncoo.eshop.price.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.alibaba.fastjson.JSONObject;
import com.roncoo.eshop.price.mapper.ProductPriceMapper;
import com.roncoo.eshop.price.model.ProductPrice;
import com.roncoo.eshop.price.service.ProductPriceService;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

	@Autowired
	private ProductPriceMapper productPriceMapper;
	@Autowired
	private JedisPool jedisPool;
	
	public void add(ProductPrice productPrice) {
		productPriceMapper.add(productPrice); 
		Jedis jedis = jedisPool.getResource();
		jedis.set("product_price_" + productPrice.getProductId(), JSONObject.toJSONString(productPrice));
	}

	public void update(ProductPrice productPrice) {
		productPriceMapper.update(productPrice);
		Jedis jedis = jedisPool.getResource();
		jedis.set("product_price_" + productPrice.getProductId(), JSONObject.toJSONString(productPrice));
	}

	public void delete(Long id) {
		ProductPrice productPrice = findById(id);
		productPriceMapper.delete(id); 
		Jedis jedis = jedisPool.getResource();
		jedis.del("product_price_" + productPrice.getProductId());
	}

	public ProductPrice findById(Long id) {
		// 先从redis里面查，如果没有再从mysql里面查，查到了以后再刷新回redis
		// 大家想一想，有没有觉得这个场景似曾相识啊
		// 这不就是我们之前讲解的那个mysql+redis双写一致性的问题场景+解决方案
		return productPriceMapper.findById(id);
	}

	public ProductPrice findByProductId(Long productId) {
		Jedis jedis = jedisPool.getResource();
		String dataJSON = jedis.get("product_price_" + productId);
		if(dataJSON != null && !"".equals(dataJSON)) {
			JSONObject dataJSONObject = JSONObject.parseObject(dataJSON);
			dataJSONObject.put("id", "-1");  
			return JSONObject.parseObject(dataJSONObject.toJSONString(), ProductPrice.class);
		} else {
			return productPriceMapper.findByProductId(productId);
		}
	}

}
