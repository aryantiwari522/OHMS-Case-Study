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

@Document("Room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
	@Id
	private String roomId;
	@NotNull(message="Room Type cannnot be null")
	private String roomType;
	@NotNull(message="Number of beds cannnot be null")
	private int numberOfBeds;
	private boolean status;
	private double price;
}
