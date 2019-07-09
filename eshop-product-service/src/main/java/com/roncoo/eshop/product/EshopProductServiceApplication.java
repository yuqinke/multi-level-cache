package com.roncoo.eshop.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EshopProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshopProductServiceApplication.class, args); 
	}
	
}
