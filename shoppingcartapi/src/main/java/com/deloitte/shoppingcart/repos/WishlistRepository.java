package com.deloitte.shoppingcart.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.shoppingcart.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

}
