package com.deloitte.shoppingcart.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.shoppingcart.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
	
	List<Wishlist> findAllByUserId(int userId);

}
