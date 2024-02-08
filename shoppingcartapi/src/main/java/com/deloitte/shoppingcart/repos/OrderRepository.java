package com.deloitte.shoppingcart.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloitte.shoppingcart.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findAllByUserId(int userId);

}
