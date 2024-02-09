package com.deloitte.shoppingcart.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.shoppingcart.model.Product;
import com.deloitte.shoppingcart.repos.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	private ProductRepository productRepository;
	
	ProductController(ProductRepository productRepository){
		this.productRepository = productRepository;
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@GetMapping("/products/{productId}")
	public Optional<Product> getProductById(@PathVariable("productId") int productId){
		return productRepository.findById(productId);
	}
	
	@GetMapping("/products/name/{name}")
	public List<Product> getProductByName(@PathVariable("name") String name){
		return productRepository.findByName(name);
	}
	
	@GetMapping("/products/price")
	public List<Product> getProductByPriceRange(@RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice){
		return productRepository.findByPriceRange(minPrice, maxPrice);
	}
	
	@PatchMapping("/products/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") int productId){
		ResponseEntity<String> result =  new ResponseEntity<>("PRODUCT WITH ID \""+productId+"\" DOES NOT EXIST", HttpStatus.NOT_FOUND);;
		Optional<Product> productInDB = getProductById(productId);
		
		if(productInDB.isPresent()) {
			Product existingProduct = productInDB.get();
			existingProduct.setStatus(false);
			productRepository.save(existingProduct);
			result = new ResponseEntity<>("PRODUCT WITH ID \""+productId+"\" VIRTUALLY DELETED", HttpStatus.OK);	
		}
		
		return result;
	}
	
	@PatchMapping("/products/{productId}")
	public ResponseEntity<String> updateProduct(
			@PathVariable("productId") int productId, 
			@RequestBody Product product
	){
		ResponseEntity<String> result =  new ResponseEntity<>("PRODUCT WITH ID \""+productId+"\" DOES NOT EXIST", HttpStatus.NOT_FOUND);;
		Optional<Product> productInDB = getProductById(productId);
		
		if(productInDB.isPresent()) {
			Product existingProduct = productInDB.get();
			existingProduct.setPrice(product.getPrice());
			existingProduct.setImage(product.getImage());
			existingProduct.setDescription(product.getDescription());
			existingProduct.setTotalProductsInventory(product.getTotalProductsInventory());
			productRepository.save(existingProduct);
			result = new ResponseEntity<>("PRODUCT WITH ID \""+productId+"\" UPDATED", HttpStatus.OK);	
		}
		
		return result;
	}
	
	@PostMapping("/products")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		
		ResponseEntity<String> result;
		Optional<Product> productInDB = productRepository.findOneByName(product.getName());
		
		if(productInDB.isPresent()) {
			Product existingProduct = productInDB.get();
			existingProduct.setTotalProductsInventory(existingProduct.getTotalProductsInventory() + 1);
			productRepository.save(existingProduct);
			result = new ResponseEntity<>("PRODUCT \""+product.getName()+"\" TOTAL INVENTORY INCREASED BY 1", HttpStatus.OK);	
			
		} else {
			productRepository.save(product);
			result = new ResponseEntity<>("PRODUCT \""+product.getName()+"\" ADDED", HttpStatus.CREATED);	

		}
		
		return result;
	}

}












