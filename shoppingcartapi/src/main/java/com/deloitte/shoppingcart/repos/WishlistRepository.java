package com.deloitte.shoppingcart.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.shoppingcart.model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
	
	List<Wishlist> findAllByUserId(Long userId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Wishlist WHERE productId = :productId")
	void deleteProductFromWishlist(@Param("productId") Long productId);

}
