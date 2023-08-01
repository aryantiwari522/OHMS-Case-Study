package com.ohms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ohms.model.Room;
import com.ohms.repository.RoomRepository;
import com.ohms.service.RoomService;

@SpringBootTest
class RoomServiceApplicationTests {
	
	@Autowired
	private RoomService roomService;
	
	@MockBean
	private RoomRepository roomRepository;

	@Test
	void contextLoads() {
	}
	
	@Test
	void addRoomTest() {
		Room room = new Room("F001", "Silver Single", 1, false, 2000);
		roomService.addRoom(room);
		verify(roomRepository,times(1)).save(room);
	}
	
	@Test
	void getAllRoomsTest() {
		when(roomRepository.findAll()).thenReturn(Stream
				.of(new Room("F001", "Silver Single", 1, false, 2000),
					new	Room("F002", "Silver Single", 1, false, 2000))
				.collect(Collectors.toList()));
		assertEquals(2, roomService.getAllRooms().size());
	}
	
	@Test
	void updateRoomTest() {
		Room room = new Room("F001", "Silver Single", 1, false, 2000);
		roomService.updateRoom(room);
		verify(roomRepository,times(1)).save(room);
	}
	
	@Test
	void deleteRoomTest() {
		String roomId = "F001";
		roomService.deleteRoom(roomId);
		verify(roomRepository,times(1)).deleteById(roomId);
	}
	
	@Test
	void getRoomsByStatusTest() {
		when(roomRepository.findByStatus(false)).thenReturn(Stream
				.of(new Room("F001", "Silver Single", 1, false, 2000),
					new	Room("F002", "Silver Single", 1, false, 2000))
				.collect(Collectors.toList()));
		assertEquals(2, roomService.getRoomsByStatus(false).size());
	}
}
