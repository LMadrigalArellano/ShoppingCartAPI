package com.deloitte.shoppingcart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@Column(name = "USER_ID", nullable = false, precision = 10)
	private int userId;
	
	@Column(name = "NAME", nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "LAST_NAME", length = 50)
	private String lastName;
	
	@Column(name = "BIO", length = 200)
	private String bio;
	
	@Column(name = "EMAIL", nullable = false, length = 200)
	private String email;
	
	@Column(name = "AREA_OF_INTEREST", length = 300)
	private String areaOfInterest;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAreaOfInterest() {
		return areaOfInterest;
	}

	public void setAreaOfInterest(String areaOfInterest) {
		this.areaOfInterest = areaOfInterest;
	}

}