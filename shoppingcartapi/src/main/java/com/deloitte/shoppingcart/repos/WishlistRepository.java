package com.deloitte.shoppingcart.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.shoppingcart.model.Wishlist;

@Component
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
	
	List<Wishlist> findAllByUserId(int userId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Wishlist WHERE productId = :productId")
	void deleteProductFromWishlist(@Param("productId") int productId);

}
