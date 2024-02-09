package com.deloitte.shoppingcart.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.deloitte.shoppingcart.model.Product;

@Component
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("FROM Product WHERE name = :name")
	Optional<Product> findOneByName(@Param("name") String name);

	@Query("FROM Product WHERE name LIKE %:name%")
	List<Product> findByName(@Param("name") String name);
	
	@Query(value = "SELECT * FROM products WHERE price BETWEEN :minPrice AND :maxPrice", nativeQuery = true)
	List<Product> findByPriceRange(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

}
