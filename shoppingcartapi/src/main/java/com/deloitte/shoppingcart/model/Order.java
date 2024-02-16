package com.deloitte.shoppingcart.model;

import java.sql.Timestamp;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDER_HISTORY")
public class Order {
	
	@Id
	@Column(name = "ORDER_ID", nullable = false, precision = 10)
	@GenericGenerator(name="random_id", strategy = "com.deloitte.shoppingcart.CustomRandomIDGenerator")
	@GeneratedValue(generator = "random_id")
	private Long orderId;
	
	@Column(name = "ORDER_DATE", nullable = false)
	private Timestamp orderDate;
	
	@Column(name = "USER_ID", precision = 10)
	private Long userId;
	
	@Column(name = "PRODUCT_ID", precision = 10)
	private Long productId;
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	

}
