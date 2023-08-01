package com.ohms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohms.model.BookedRooms;
import com.ohms.model.RoomDTO;
import com.ohms.repository.BookedRoomRepository;

@Service
public class BookedRoomService {
	
	@Autowired
	private BookedRoomRepository bookedRoomRepository;

	/*
	 * This method used to add booked Room to database
	 * It takes room id and date to add the room
	 * This is helpful in searching the booked room for a particular day
	 */
	
	public String addRoomToBooked(RoomDTO roomDTO ) {
		if(bookedRoomRepository.existsByDate(roomDTO.getDate())) {
			BookedRooms bookedRooms = bookedRoomRepository.findByDate(roomDTO.getDate());
			bookedRooms.getRoomIds().add(roomDTO.getRoomId());
			bookedRoomRepository.save(bookedRooms);
			return "Added room";
		}
		String id = roomDTO.getId();
		Date date = roomDTO.getDate();
		
		List<String> list = new ArrayList<>();
		list.add(roomDTO.getRoomId());
		BookedRooms bookedRooms = new BookedRooms(id, date, list);
		bookedRoomRepository.save(bookedRooms);
		return "add room";
	}
	
	/*
	 * This method is used to get all the room booked for a particular date.
	*/
	
	public BookedRooms findByDate(Date date) {
		return bookedRoomRepository.findByDate(date);
	}
	public BookedRooms findByDateDto(RoomDTO roomDTO) {
		return bookedRoomRepository.findByDate(roomDTO.getDate());
	}
	
	public boolean exitsByCheckInDate(Date date) {
		return bookedRoomRepository.existsByDate(date);
	}
}
