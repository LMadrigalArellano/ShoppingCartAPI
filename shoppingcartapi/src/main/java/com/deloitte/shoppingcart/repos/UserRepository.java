package com.deloitte.shoppingcart.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deloitte.shoppingcart.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	@Query("FROM User WHERE firstName LIKE %:name% OR lastName LIKE %:name%")
	List<User> findByName(@Param("name") String name);

}
