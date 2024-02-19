package com.deloitte.shoppingcart.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.deloitte.shoppingcart.exception.Order.OrderNotFoundException;
import com.deloitte.shoppingcart.exception.Product.ProductNotFoundException;
import com.deloitte.shoppingcart.exception.User.UserAlreadyExistsException;
import com.deloitte.shoppingcart.exception.User.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(
											LocalDateTime.now(), 
											ex.getMessage(),
											request.getDescription(false)
										);
		return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	/////////////////////////////////---START PRODUCT HANDLERS---/////////////////////////////////

	
	@ExceptionHandler(ProductNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleProductNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(
											LocalDateTime.now(), 
											ex.getMessage(),
											request.getDescription(false)
										);
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	/////////////////////////////////---END PRODUCT HANDLERS---/////////////////////////////////
	
	/////////////////////////////////---START USER HANDLERS---/////////////////////////////////
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(
											LocalDateTime.now(), 
											ex.getMessage(),
											request.getDescription(false)
										);
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public final ResponseEntity<ErrorDetails> handleUserAlreadyExistsFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(
											LocalDateTime.now(), 
											ex.getMessage(),
											request.getDescription(false)
										);
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.CONFLICT);
		
	}
	
	/////////////////////////////////---END USER HANDLERS---/////////////////////////////////
	
	/////////////////////////////////---START ORDER HANDLERS---/////////////////////////////////
	@ExceptionHandler(OrderNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleOrderNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(
											LocalDateTime.now(), 
											ex.getMessage(),
											request.getDescription(false)
										);
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	/////////////////////////////////---END ORDER HANDLERS---/////////////////////////////////



}
