package com.deloitte.shoppingcart.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product {
	
	@Id
	@Column(name = "PRODUCT_ID", nullable = false, precision = 10)
	@GenericGenerator(name="random_id", strategy = "com.deloitte.shoppingcart.CustomRandomIDGenerator")
	@GeneratedValue(generator = "random_id")
	private Long productId;
	
	@Column(name = "NAME", nullable = false, length = 100)
	private String name;
	
	@Column(name = "PRICE", nullable = false, precision = 10)
	private double price;
	
	@Lob
	private byte[] image;
	
	@Column(name = "DESCRIPTION", length = 200)
	private String description;
	
	@Column(name = "TOTAL_PRODUCTS_INVENTORY", nullable = false, precision = 10)
	private int totalProductsInventory;
	
	@Column(name = "STATUS", nullable = false)
	private boolean status;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotalProductsInventory() {
		return totalProductsInventory;
	}

	public void setTotalProductsInventory(int totalProductsInventory) {
		this.totalProductsInventory = totalProductsInventory;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
