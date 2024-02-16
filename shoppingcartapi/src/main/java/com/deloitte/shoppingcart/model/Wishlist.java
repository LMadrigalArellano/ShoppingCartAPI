package com.deloitte.shoppingcart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "WISHLIST")
public class Wishlist {
	
	@Id
	@Column(name = "WISH_ID", nullable = false, precision = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wishId;
	
	@Column(name = "USER_ID", precision = 10)
	private int userId;
	
	@Column(name = "PRODUCT_ID", precision = 10)
	private int productId;

	public int getWishId() {
		return wishId;
	}

	public void setWishId(int wishId) {
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
