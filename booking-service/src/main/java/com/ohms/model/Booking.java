package com.ohms.model;

import java.time.LocalDate;
import java.util.Date;

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
@Document("Booking")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	
	@Id
	private int bookingId;
	private String roomId;
	private int guestId;
	private Date checkInDate;
	private Date checkOutDate;
	private boolean checkInStatus;
	private boolean chekOutStatus;
	private double totalPrice;
	private String paymentMode;
	private boolean paymentStatus;
	private String bookingStatus;
}
