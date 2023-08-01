package com.ohms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ohms.model.Product;
import com.ohms.repository.InventoryRepository;
import com.ohms.service.InventoryService;


@SpringBootTest
class InventoryServiceApplicationTests {

	@Autowired
	private InventoryService inventoryService;
	
	@MockBean
	private InventoryRepository inventoryRepository;
	
	@Test
	void addProductTest() {
		Product product = new Product(221, "Towel", "Inventory",20);
		inventoryService.addProduct(product);
		verify(inventoryRepository,times(1)).save(product);
	}
	
	@Test
	void getAllProductsTest() {
		when(inventoryRepository.findAll()).thenReturn(Stream
				.of(new Product(221, "Towel", "Inventory",20),
					new	Product(222, "Towel1", "Inventory1",21))
				.collect(Collectors.toList()));
		assertEquals(2, inventoryService.getAllProducts().size());
	}
	
	@Test
	void updateProductTest() {
		Product product = new Product(221, "Towel", "Inventory",20);
		inventoryService.updateProduct(product);
		verify(inventoryRepository,times(1)).save(product);
	}
	
	@Test
	void deleteRoomTest() {
		int productId = 221;
		inventoryService.deleteProduct(productId);
		verify(inventoryRepository,times(1)).deleteById(productId);
	}

}
