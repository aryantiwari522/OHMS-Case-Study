package com.ohms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohms.model.Product;
import com.ohms.model.Response;
import com.ohms.service.InventoryService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * This is the controller class of Inventory service Which is created for REST API
 *
 */
@RestController
@RequestMapping("/inventory")
@CrossOrigin("*")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	// For logging errors
	Logger logger = LoggerFactory.getLogger(InventoryController.class);
	
	//To add new Product into Inventory Database
	
	@PostMapping("/add")
	@Operation(summary = "To add new Product into Inventory Database")
	//@PreAuthorize("hasRole('OWNER') or hasRole('MANAGER')")
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		try {
			inventoryService.addProduct(product);
			return ResponseEntity.ok(new Response("Product Added"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new Response("Error while adding product"));
		}
	}
	
	//Return all the Product Details in the form of list
	
	@GetMapping("/get")
	@Operation(summary = "Return all the Product Details in the form of list")
	//@PreAuthorize("hasRole('OWNER') or hasRole('MANAGER')")
	public List<Product> getAllProducts(){
		try {
			return inventoryService.getAllProducts();
		} catch (Exception e) {
			logger.error(e.toString());
			return new ArrayList<Product>(null);
		}
	}
	
	// Return Product Detail for requested id
	
	@GetMapping("/get/{productId}")
	@Operation(summary = "Return Product Detail for requested id")
	//@PreAuthorize("hasRole('OWNER') or hasRole('MANAGER')")
	public Optional<Product> getProductById(@PathVariable int productId) {
		try {
			return inventoryService.getProductById(productId);
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		}
	}
	
	// Update Product Detail for requested Id
	
	@PutMapping("/update")
	@Operation(summary = "Update Product Detail for requested Id")
	//@PreAuthorize("hasRole('OWNER') or hasRole('MANAGER')")
	public ResponseEntity<?> updateProduct(@RequestBody Product product) {
		try {
			inventoryService.updateProduct(product);
			return ResponseEntity.ok(new Response("Updated the product"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new Response("Error while updating product"));
			
		}
	}
	
	// Delete Product Detail for requested Id
	
	@DeleteMapping("/delete/{productId}")
	@Operation(summary = "Delete Product Detail for requested Id")
	//@PreAuthorize("hasRole('OWNER') or hasRole('MANAGER')")
	public ResponseEntity<?> deleteProduct(@PathVariable int productId) {
		try {
			inventoryService.deleteProduct(productId);
			return ResponseEntity.ok(new Response("Product Deleted"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new Response("Error while Deleting Product"));
		}
	}
}
