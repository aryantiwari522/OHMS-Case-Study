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

import com.ohms.model.Room;
import com.ohms.model.RoomResponse;
import com.ohms.service.RoomService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * This is the controller class of Room service Which is created for REST API
 *
 */

@RestController
@RequestMapping("/room")
@CrossOrigin("http://localhost:4200/")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	// For logging errors
	Logger logger = LoggerFactory.getLogger(RoomController.class);
	
	//To add new Room detail
	
	@PostMapping("/add")
	@Operation(summary = "To add new Room into Room Database")
	public ResponseEntity<?> addRoom(@RequestBody Room room) {
		try {
			roomService.addRoom(room);
			return ResponseEntity.ok(new RoomResponse("Room Added Successfully"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new RoomResponse("Error while adding Room"));
		}
	}
	
	//  Return all the room in form of list
	
	@GetMapping("/get")
	@Operation(summary = "Return all the Room Details in the form of list")
	public List<Room> getAllRooms(){
		try {
			return roomService.getAllRooms();
		} catch (Exception e) {
			logger.error(e.toString());
			return new ArrayList<Room>(null);
		}
	}
	
	// Return the room detail for requested the roomId
	
	@GetMapping("/get/{roomId}")
	@Operation(summary = "Return Room Detail for requested id")
	public Optional<Room> getRoomById(@PathVariable String roomId) {
		try {
			return roomService.getRoomById(roomId);
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		}
	}
	
	// Update Room Detail for requested Id
	
	@PutMapping("/update")
	@Operation(summary = "Update Room Detail for requested Id")
	public ResponseEntity<?> updateRoom(@RequestBody Room room) {
		try {
			roomService.updateRoom(room);
			return ResponseEntity.ok(new RoomResponse("Updated the Room Detail"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new RoomResponse("Error while updating Room Detail"));
		}
	}
	
	//Delete Room Detail for requested Id
	
	@DeleteMapping("/delete/{roomId}")
	@Operation(summary = "Delete Room Detail for requested Id")
	public ResponseEntity<?> deleteRoom(@PathVariable String roomId) {
		try {
			roomService.deleteRoom(roomId);
			return ResponseEntity.ok(new RoomResponse("Room Deleted"));
		} catch (Exception e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body(new RoomResponse("Error while Deleting Room"));
		}
	}
	
	// Return all the Room Details which have selected status in the form of list
	
	@GetMapping("/getbystatus/{status}")
	@Operation(summary = "Return all the Room Details which have selected status in the form of list")
	public List<Room> getRoomsByStatus(@PathVariable boolean status){
		return roomService.getRoomsByStatus(status);
	}
	
	//Return the price for respective Id
	
	@GetMapping("/getroomprice/{roomId}")
	@Operation(summary = "Return the price for respective Id")
	public double getRoomPrice(@PathVariable String roomId) {
		return roomService.getRoomPrice(roomId);
	}

}
