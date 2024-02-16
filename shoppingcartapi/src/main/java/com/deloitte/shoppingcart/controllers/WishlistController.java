package com.deloitte.shoppingcart.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	/////////////////////////////////---START GET OPERATIONS---/////////////////////////////////

	@GetMapping("/wishlists")
	public List<Wishlist> getAllWishlists() {
		return wishlistRepository.findAll();
	}
	
	@GetMapping("/wishlists/user/{userId}")
	public List<Wishlist> getWishlistByUserId(@PathVariable("userId") int userId){
		return wishlistRepository.findAllByUserId(userId);
	}
	
	/////////////////////////////////---END GET OPERATIONS---/////////////////////////////////
	
	/////////////////////////////////---START POST OPERATIONS---/////////////////////////////////
	
	@PostMapping("/wishlists")
	public void saveProductToWishList(@RequestBody Wishlist wishlist) {
		wishlistRepository.save(wishlist);
	}
	
	/////////////////////////////////---END POST OPERATIONS---/////////////////////////////////

	
	
	
}