package com.deloitte.shoppingcart.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.shoppingcart.model.Order;
import com.deloitte.shoppingcart.repos.OrderRepository;
import com.deloitte.shoppingcart.repos.UserRepository;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	private OrderRepository orderRepository;
	private UserRepository userRepository;
	
	OrderController(OrderRepository orderRepository, UserRepository userRepository){
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}
	
	
	@GetMapping("/orders")
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}
	
	@GetMapping("/orders/user/{userId}")
	public List<Order> getAllOrdersByUserId(@PathVariable("userId") int userId){
		return orderRepository.findAllByUserId(userId);
	}
	
	@DeleteMapping("/orders/delete/{orderId}")
	public ResponseEntity<String> deleteOrderById(@PathVariable("orderId") int orderId){
		
		ResponseEntity<String> result = new ResponseEntity<>("USER WITH ID \""+orderId+"\" DOES NOT EXIST", HttpStatus.NOT_FOUND);	

		if(orderRepository.findById(orderId).isEmpty()) {
			result = new ResponseEntity<>("NO RECORD OF ORDER WITH ID \""+orderId+"\"", HttpStatus.BAD_REQUEST);
			
		}else {
			try {
				orderRepository.deleteById(orderId);
				result = new ResponseEntity<>("ORDER WITH ID \""+orderId+"\" DELETED", HttpStatus.OK);	

			} catch(Error e) {
				result = new ResponseEntity<>("ERROR DELETING ORDER WITH ID \""+orderId+"\" HAD NO ORDERS", HttpStatus.BAD_REQUEST);

			}
		}
		
		return result;
	}
	
	@DeleteMapping("orders/delete/user/{userId}")
	public ResponseEntity<String> deleteOrdersByUserId(@PathVariable("userId") int userId){
		
		ResponseEntity<String> result = new ResponseEntity<>("USER WITH ID \""+userId+"\" DOES NOT EXIST", HttpStatus.NOT_FOUND);	

		if(userRepository.findById(userId).isPresent()) {
			List<Order> ordersInDB = getAllOrdersByUserId(userId);
			
			if(ordersInDB.isEmpty()) {
				result = new ResponseEntity<>("USER WITH ID \""+userId+"\" HAD NO ORDERS", HttpStatus.OK);
				
			} else {
				ordersInDB.forEach(currentOrder -> {
					deleteOrderById(currentOrder.getOrderId());
				});
				
				result = new ResponseEntity<>("ORDERS FROM USER \""+userId+"\" DELETED", HttpStatus.OK);
			}
		}
		
		return result;
	}
	

}
