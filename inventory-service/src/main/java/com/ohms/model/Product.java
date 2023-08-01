package com.ohms.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class to store the data and fetch the data from database
 *
 */
@Document("Product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	private int productId;
	@NotNull(message="Product Name cannnot be null")
	private String productName;
	@NotNull(message="Product Category cannnot be null")
	private String productCategory;
	private int quantity;
}
