package com.roncoo.eshop.product.rabbitmq;

public interface RabbitQueue {

	public static final String DATA_CHANGE_QUEUE = "data-change-queue";
	public static final String REFRESH_DATA_CHANGE_QUEUE = "refresh-data-change-queue";
	public static final String HIGH_PRIORITY_DATA_CHANGE_QUEUE = "high-priority-data-change-queue";
	
}
