package com.ohms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ohms.model.Product;

/**
 * The Inventory Repository interface used to extends function from MongoRepository Class.
 *
 */
@Repository
public interface InventoryRepository extends MongoRepository<Product, Integer> {

}
