package com.deloitte.shoppingcart.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDER_HISTORY")
public class Order {
	
	@Id
	@Column(name = "ORDER_ID", nullable = false, precision = 10)
	private int orderId;
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Column(name = "ORDER_DATE", nullable = false)
	private Timestamp orderDate;
	
	@Column(name = "USER_ID", precision = 10)
	private int userId;
	
	@Column(name = "PRODUCT_ID", precision = 10)
	private int productId;
	

}
