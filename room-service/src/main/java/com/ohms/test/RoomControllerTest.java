package com.ohms.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ohms.controller.RoomController;
import com.ohms.model.Room;
import com.ohms.service.RoomService;

class RoomControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
    }

    @Test
    void testAddRoom() throws Exception {
        Room room = new Room();
        room.setRoomId("1");
        room.setRoomType("101");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(room);

        mockMvc.perform(post("/room/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        // Add assertions for the response if needed
    }

    @Test
    void testGetAllRooms() throws Exception {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("1", "101",2,true,1000.0));
        rooms.add(new Room("2", "102",3,false,1500.0));

        when(roomService.getAllRooms()).thenReturn(rooms);

        mockMvc.perform(get("/room/get"))
                .andExpect(status().isOk());

        // Add assertions for the response if needed
    }

    @Test
    void testGetRoomById() throws Exception {
        String roomId = "1";
        Room room = new Room(roomId, "101",2,true,1000.0);

        when(roomService.getRoomById(String.valueOf(roomId))).thenReturn(Optional.of(room));

        mockMvc.perform(get("/room/get/{roomId}", roomId))
                .andExpect(status().isOk());

        // Add assertions for the response if needed
    }

    @Test
    void testUpdateRoom() throws Exception {
        Room room = new Room("1", "101",2,true,1000.0);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(room);

        mockMvc.perform(put("/room/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        // Add assertions for the response if needed
    }

    @Test
    void testDeleteRoom() throws Exception {
        String roomId = "2";

        mockMvc.perform(delete("/room/delete/{roomId}", roomId))
                .andExpect(status().isOk());

        // Add assertions for the response if needed
    }

    @Test
    void testGetRoomsByStatus() throws Exception {
        boolean status = true;
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("1", "101",2,true,1000.0));
        rooms.add(new Room("2", "102",3,false,1500.0));

        when(roomService.getRoomsByStatus(status)).thenReturn(rooms);

        mockMvc.perform(get("/room/getbystatus/{status}", status))
                .andExpect(status().isOk());

        // Add assertions for the response if needed
    }

    @Test
    void testGetRoomPrice() throws Exception {
        String roomId = "1";
        double price = 1000.0;

        when(roomService.getRoomPrice(String.valueOf(roomId))).thenReturn(price);

        mockMvc.perform(get("/room/getroomprice/{roomId}", roomId))
                .andExpect(status().isOk());

        // Add assertions for the response if needed
    }
}

