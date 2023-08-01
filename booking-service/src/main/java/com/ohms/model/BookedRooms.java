package com.ohms.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class to store the data and fetch the data from database
 * BookedRoom is used to store the booked rooms for particular day 
 *
 */
@Document("BookedRooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRooms {
	
	@Id
	private String id;
	private Date date;
	private List<String> roomIds;

}
