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

import com.ohms.model.Guest;
import com.ohms.model.GuestResponse;
import com.ohms.service.GuestService;

import io.swagger.v3.oas.annotations.Operation;


/**
 * This is the controller class of Guest service Which is created for REST API
 *
 */

@RestController
@RequestMapping("/guest")
@CrossOrigin("*")
public class Controller {
	
	@Autowired
	private GuestService guestService;
	
	Logger logger = LoggerFactory.getLogger(Controller.class);
	@Autowired
	public void setGuestService(GuestService guestService) {
		this.guestService = guestService;
	}
	
	@PostMapping("/add")
	@Operation(summary = "To add new Guest into Guest Database")
	public ResponseEntity<?> addGuest(@RequestBody Guest guest) {		
		try {
			if(guestService.isExistedByEmailId(guest.getGuestEmailId())) {
				Guest guestVar = guestService.getGuestByEmailId(guest.getGuestEmailId());
				return ResponseEntity.ok(new GuestResponse("Guest Already Exist", guestVar.getGuestId(), guestVar.getGuestEmailId()));
			}else {
				guestService.addGuest(guest);
				return ResponseEntity.ok(new GuestResponse("Guest Detail Add successfully", guest.getGuestId(), guest.getGuestEmailId()));
			}
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new GuestResponse("Problem in Adding Guest Detail", 0, ""));
		}
	}
	
	@GetMapping("/get")
	@Operation(summary = "Return all the Guest Details in the form of list")
	public List<Guest> getAllguest(){
		try {
			return guestService.getAllGuests();
		} catch (Exception e) {
			logger.error(e.toString());
			return new ArrayList<Guest>();
		}
	}
	
	@GetMapping("/get/{id}")
	@Operation(summary = "Return Guest Detail for requested id")
	public Optional<Guest> getGuestById(@PathVariable int id) {
		try {
			return guestService.getGuestById(id);
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		}
	}
	
	@GetMapping("/getByEmail/{emailId}")
	@Operation(summary = "Return Guest Detail for requested Email Id")
	public Guest getGuestByEmailId(@PathVariable String emailId) {
		try {
			return guestService.getGuestByEmailId(emailId);
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		}
	}
	
	@PutMapping("/update")
	@Operation(summary = "Update Guest Detail for requested Id")
	public ResponseEntity<?> updateGuest(@RequestBody Guest guest) {
		try {
			this.guestService.updateGuest(guest);
			 return ResponseEntity.ok(new GuestResponse("Updated Guest", 0, ""));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new GuestResponse("Problem in updating Guest", 0, ""));
		}
	}
	
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete Guest Detail for requested Id")
	public ResponseEntity<?> deleteGuest(@PathVariable int id) {
		try {
			this.guestService.deleteGuest(id);
			return ResponseEntity.ok(new GuestResponse("Deleted Guest", 0, ""));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new GuestResponse("Problem in UpdatingGuest Detail", 0, ""));
		}
	}
	
	@GetMapping("/getemailid/{guestId}")
	@Operation(summary = "Return Email Id for requested guestID")
	public String getEmailID(@PathVariable int guestId) {
		try {
			return guestService.getEmailID(guestId);
		} catch (Exception e) {
			logger.error(e.toString());
			return "Error";
		}
	}

}
