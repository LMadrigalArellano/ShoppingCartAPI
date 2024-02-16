package com.deloitte.shoppingcart.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "WISHLIST")
public class Wishlist {
	
	@Id
	@Column(name = "WISH_ID", nullable = false, precision = 10)
	@GenericGenerator(name="random_id", strategy = "com.deloitte.shoppingcart.CustomRandomIDGenerator")
	@GeneratedValue(generator = "random_id")
	private Long wishId;
	
	@Column(name = "USER_ID", precision = 10)
	private int userId;
	
	@Column(name = "PRODUCT_ID", precision = 10)
	private int productId;

	public Long getWishId() {
		return wishId;
	}

	public void setWishId(Long wishId) {
		this.wishId = wishId;
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

}
