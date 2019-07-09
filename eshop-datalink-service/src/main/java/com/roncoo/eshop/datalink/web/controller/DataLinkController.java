package com.roncoo.eshop.datalink.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.alibaba.fastjson.JSONObject;
import com.roncoo.eshop.datalink.service.EshopProductService;

@RestController
public class DataLinkController {

	@Autowired
	private EshopProductService eshopProductService;
	@Autowired
	private JedisPool jedisPool;
	
	@RequestMapping("/getProduct")
	@ResponseBody
	public String getProduct(Long productId) {
		// 先读本地的ehcache，但是我们这里就不做了，因为之前都演示过了，大家自己做就可以了
		
		// 读redis主集群
		Jedis jedis = jedisPool.getResource();
		String dimProductJSON = jedis.get("dim_product_" + productId);
		
		if(dimProductJSON == null || "".equals(dimProductJSON)) {
	    	String productDataJSON = eshopProductService.findProductById(productId);
	    	
	    	if(productDataJSON != null && !"".equals(productDataJSON)) {
	    		JSONObject productDataJSONObject = JSONObject.parseObject(productDataJSON);
	    		
	    		String productPropertyDataJSON = eshopProductService.findProductPropertyByProductId(productId);
	    		if(productPropertyDataJSON != null && !"".equals(productPropertyDataJSON)) {
	    			productDataJSONObject.put("product_property", JSONObject.parse(productPropertyDataJSON));
	    		} 
	    		
	    		String productSpecificationDataJSON = eshopProductService.findProductSpecificationByProductId(productId);
	    		if(productSpecificationDataJSON != null && !"".equals(productSpecificationDataJSON)) {
	    			productDataJSONObject.put("product_specification", JSONObject.parse(productSpecificationDataJSON));
	    		}
	    		
	    		jedis.set("dim_product_" + productId, productDataJSONObject.toJSONString());
	    		
	    		return productDataJSONObject.toJSONString();
	    	} 
		}
		
		return "";
	}
	
}
