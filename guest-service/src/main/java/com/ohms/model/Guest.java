package com.ohms.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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


@Document(collection = "Guest")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Guest {
	@Id
	private int guestId;
	
	@NotNull(message="Guest Name cannnot be null")
	private String guestName;
	
	@NotNull(message="Guest Age cannnot be null")
	private int guestAge;
	
	@NotNull(message="Guest Contact Number cannnot be null")
	@Size(min = 10, message = "Contact Number Cannot be less than 10 digits")
	@Size(max = 10, message = "Contact Number Cannot be more than 10 digits")
	private long guestContactNumber;
	
	@NotNull(message="Guest Email Id cannnot be null")
	private String guestEmailId;
	
	@NotNull(message="Guest Address cannnot be null")
	private String guestAddress;
}
