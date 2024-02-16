package com.deloitte.shoppingcart.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.deloitte.shoppingcart.model.User;
import com.deloitte.shoppingcart.repos.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	/////////////////////////////////---START SET UP---/////////////////////////////////

	private UserRepository userRepository;
	private OrderController orderController;
	
	UserController(UserRepository userRepository, OrderController orderController){
		this.userRepository = userRepository;
		this.orderController = orderController;
	}
	
	/////////////////////////////////---END SET UP---/////////////////////////////////

	
	/////////////////////////////////---START GET OPERATIONS---/////////////////////////////////

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{userId}")
	public Optional<User> getUserById(@PathVariable("userId") int userId) {
		return userRepository.findById(userId);
	}
	
	@GetMapping("/users/name/{name}")
	public List<User> getUserByName(@PathVariable("name") String name) {
		return userRepository.findByName(name);
	}
	
	@GetMapping("/users/email/{email}")
	public Optional<User> getUserByEmail(@PathVariable("email") String email) {
		return userRepository.findByEmail(email);
	}
	
	/////////////////////////////////---END GET OPERATIONS---/////////////////////////////////

	
	/////////////////////////////////---START PATCH OPERATIONS---/////////////////////////////////

	@PatchMapping("users/{userId}")
	public ResponseEntity<String> updateUser(
			@PathVariable("userId") int userId,
			@RequestBody User newUser
	) {
		ResponseEntity<String> result = new ResponseEntity<>("USER WITH ID \""+userId+"\" UPDATED", HttpStatus.OK);
		Optional<User> userInDB = getUserById(userId);
		boolean isNewEmailAvailable = getUserByEmail(newUser.getEmail()).isEmpty();
		
		if(userInDB.isEmpty()) {
			result = new ResponseEntity<>("USER WITH ID \""+userId+"\" DOES NOT EXIST", HttpStatus.NOT_FOUND);	
			
		} else {
			
			if(isNewEmailAvailable) {
				userInDB.ifPresent(user -> {
					if(newUser.getEmail() != null) {
						user.setEmail(newUser.getEmail());
					}
					
					if(newUser.getAreaOfInterest() != null) {
						user.setAreaOfInterest(newUser.getAreaOfInterest());	
					}
					
					userRepository.save(user);
				});	
			} else {
				result = new ResponseEntity<>("THE EMAIL \""+newUser.getEmail()+"\" IS ALREADY REGISTERED", HttpStatus.CONFLICT);
				
			}
		}

		return result;
	}
	
	/////////////////////////////////---END PATCH OPERATIONS---/////////////////////////////////

	
	/////////////////////////////////---START POST OPERATIONS---/////////////////////////////////

	@PostMapping("/users")
	public ResponseEntity<String> saveUser(@RequestBody User user) {

		ResponseEntity<String> result;
		boolean emailIsNotRegistered = getUserByEmail(user.getEmail()).isEmpty();
		
		if(emailIsNotRegistered) {
			result = new ResponseEntity<>("USER \""+user.getFirstName()+"\" CREATED", HttpStatus.CREATED);	
			userRepository.save(user);
			
		}else {
			result = new ResponseEntity<>("THE EMAIL \""+user.getEmail()+"\" IS ALREADY REGISTERED", HttpStatus.CONFLICT);
			
		}
		
		return result;
	}
	
	/////////////////////////////////---END POST OPERATIONS---/////////////////////////////////

		
	/////////////////////////////////---START DELETE OPERATIONS---/////////////////////////////////

	@DeleteMapping("/users/delete/{userId}")
	public ResponseEntity<String> deleteUserAndClearOrderHistory(@PathVariable("userId") int userId){
		
		ResponseEntity<String> result = new ResponseEntity<>("USER WITH ID \""+userId+"\" WAS NOT REGISTERED", HttpStatus.BAD_REQUEST);	;
		
		if(getUserById(userId).isPresent()) {
			orderController.deleteOrdersByUserId(userId);
			userRepository.deleteById(userId);
			result = new ResponseEntity<>("USER WITH ID \""+userId+"\" DELETED", HttpStatus.OK);	
		}
		
		return result;
		
	}
	
	/////////////////////////////////---END DELETE OPERATIONS---/////////////////////////////////


}
