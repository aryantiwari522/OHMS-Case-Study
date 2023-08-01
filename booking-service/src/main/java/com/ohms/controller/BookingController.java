package com.ohms.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import com.ohms.model.Booking;
import com.ohms.model.Payment;
import com.ohms.model.ResponseMessage;
import com.ohms.model.Room;
import com.ohms.service.BookingService;

@RestController
@RequestMapping("/booking")
@CrossOrigin("*")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	
	@PostMapping("/add")
	@Operation(summary = "To add new Booking Detail")
	public ResponseEntity<?> addBookingDetail(@RequestBody Booking booking) {
		return bookingService.addBookingDetail(booking);
	}
	
	@GetMapping("/get")
	@Operation(summary = "Return List of booking details")
	public List<Booking> getAllBookingDetails(){
		return bookingService.getAllBookingDetails();
	}
	
	@PostMapping("/payment")
	@Operation(summary = "Update payment details")
	public ResponseEntity<?> addPaymentDetails(@RequestBody Payment payment) {
		try {
			bookingService.addPaymentDetail(payment.getBookingID(), payment);
			return ResponseEntity.ok(new ResponseMessage("Booking Successfully"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new ResponseMessage("Error in updating payment details"));
		}
	}
	
	@GetMapping("/cancel/{bookingId}")
	@Operation(summary = "Cancel the existing booking")
	public ResponseEntity<?> cancelBookink(@PathVariable int bookingId) {
		try {
			bookingService.cancelBooking(bookingId);
			return ResponseEntity.ok(new ResponseMessage("Booking canclled Successfully"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new ResponseMessage("Error in updating payment details"));
		}
	}
	
	@GetMapping("/checkin/{bookingId}")
	@Operation(summary = "Check In")
	public ResponseEntity<?> checkIn(@PathVariable int bookingId){
		try {
			bookingService.checkIn(bookingId);
			return ResponseEntity.ok(new ResponseMessage("Check In Successfully"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new ResponseMessage("Error in Check In"));
		}
	}
	
	@GetMapping("/checkout/{bookingId}")
	@Operation(summary = "Check In")
	public ResponseEntity<?> checkOut(@PathVariable int bookingId){
		try {
			bookingService.checkOut(bookingId);
			return ResponseEntity.ok(new ResponseMessage("Check Out Successfully"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new ResponseMessage("Error Out Check In"));
		}
	}
	
	@GetMapping("/get/{bookingId}")
	@Operation(summary = "Return booking detail for requested bookingId")
	public Booking getBookingById(@PathVariable int bookingId) {
		try {
			return bookingService.getBookingById(bookingId);
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		}		
	}
	
	@GetMapping("/getbycheckindate/{checkInDate}")
	@Operation(summary = "Return List of booking detail for particular checkInDate")
	public List<Booking> getBookingsByCheckInDate(@PathVariable Date checkInDate){
		return bookingService.getBookingsByCheckInDate(checkInDate);
	}
	
	
	@GetMapping("/getavilaberoom/{checkindate}/{checkoutdate}")
	public List<Room> getAvilableRoom(@PathVariable Date checkindate, @PathVariable Date checkoutdate){
		try {
			return bookingService.getAvilableRoom(checkindate, checkoutdate);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ArrayList<Room>();
		}
	}
}
