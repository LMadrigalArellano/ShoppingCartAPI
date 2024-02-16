package com.deloitte.shoppingcart.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.shoppingcart.model.Wishlist;
import com.deloitte.shoppingcart.repos.WishlistRepository;

@RestController
@RequestMapping("/api")
public class WishlistController {

	private WishlistRepository wishlistRepository;
	
	WishlistController(WishlistRepository wishlistRepository){
		this.wishlistRepository = wishlistRepository;
	}
	
	@GetMapping("/wishlists")
	public List<Wishlist> getAllWishlists() {
		return wishlistRepository.findAll();
	}
}
