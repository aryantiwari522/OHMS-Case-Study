package com.ohms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohms.model.Product;
import com.ohms.repository.InventoryRepository;

/**
 * This Class contains the business logics for the Inventory service.
 *
 */

@Service
public class InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private SequenceService sequenceService;
	
	// It takes product as input and add in database
	public void addProduct(Product product) {
		int getId = sequenceService.getNextSequence("product");
		product.setProductId(getId);
		inventoryRepository.save(product);
	}
	
	// It returns list of product
	public List<Product> getAllProducts(){
		return inventoryRepository.findAll();
	}
	
	// Return product detail for requested id
	public Optional<Product> getProductById(int productId) {
		return inventoryRepository.findById(productId);
	}
	
	// Updates the product detail
	public void updateProduct(Product product) {
		inventoryRepository.save(product);
	}
	
	// Delete the product detail
	public void deleteProduct(int productId) {
		inventoryRepository.deleteById(productId);
	}

}
