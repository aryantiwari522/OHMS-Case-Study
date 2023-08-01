package com.ohms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohms.model.BookedRooms;
import com.ohms.model.RoomDTO;
import com.ohms.service.BookedRoomService;

@RestController
@RequestMapping("/bookedrooms")
public class BookedRoomController {
	
	@Autowired
	private BookedRoomService bookedRoomService;
	
	@PostMapping("/add")
	public String add(@RequestBody RoomDTO roomDTO) {
		return bookedRoomService.addRoomToBooked(roomDTO);
	}
	
	@PostMapping("/get")
	public BookedRooms getBookedRoom(@RequestBody RoomDTO roomDTO) {
		return bookedRoomService.findByDateDto(roomDTO);
	}

}
