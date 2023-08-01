package com.ohms.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ohms.model.Guest;
import com.ohms.model.GuestResponse;
import com.ohms.service.GuestService;
import com.ohms.controller.Controller;

class ControllerTest {
	private Controller controller;
	
	@Autowired
	private GuestService guestService;

	@BeforeEach
	void setUp() {
		guestService = mock(GuestService.class);
		controller = new Controller();
		controller.setGuestService(guestService);
	}

	@Test
	void testAddGuest() {
		Guest guest = new Guest();
		guest.setGuestEmailId("test@example.com");

		when(guestService.isExistedByEmailId("test@example.com")).thenReturn(false);
		 doNothing().when(guestService).addGuest(any(Guest.class));

		ResponseEntity<?> response = controller.addGuest(guest);

		// Assert the response
		GuestResponse expectedResponse = new GuestResponse("Guest Detail Add successfully", guest.getGuestId(),
				guest.getGuestEmailId());
		assertResponseEntityEquals(expectedResponse, response);
	}

	@Test
	void testAddGuestGuestExists() {
	    Guest guest = new Guest();
	    guest.setGuestEmailId("test@example.com");
	    guest.setGuestId(1); // Set the guestId

	    when(guestService.isExistedByEmailId("test@example.com")).thenReturn(true);
	    when(guestService.getGuestByEmailId("test@example.com")).thenReturn(guest);

	    ResponseEntity<?> response = controller.addGuest(guest);

	    // Assert the response
	    GuestResponse expectedResponse = new GuestResponse("Guest Already Exist", guest.getGuestId(),
	        guest.getGuestEmailId());
	    assertResponseEntityEquals(expectedResponse, response);
	}


	@Test
	void testAddGuestException() {
		Guest guest = new Guest();
		guest.setGuestEmailId("test@example.com");

		when(guestService.isExistedByEmailId("test@example.com")).thenReturn(false);
		doThrow(new RuntimeException("Test Exception")).when(guestService).addGuest(any(Guest.class));

		ResponseEntity<?> response = controller.addGuest(guest);

		// Assert the response
		GuestResponse expectedResponse = new GuestResponse("Problem in Adding Guest Detail", 0, "");
		assertResponseEntityEquals(expectedResponse, response);
	}

	@Test
	void testGetAllGuests() {
		List<Guest> guests = new ArrayList<>();
		guests.add(new Guest());
		guests.add(new Guest());

		when(guestService.getAllGuests()).thenReturn(guests);

		List<Guest> response = controller.getAllguest();

		// Assert the response
		assertIterableEquals(guests, response);
	}

	@Test
	void testGetGuestById() {
		int guestId = 1;
		Optional<Guest> guest = Optional.of(new Guest());

		when(guestService.getGuestById(guestId)).thenReturn(guest);

		Optional<Guest> response = controller.getGuestById(guestId);

		// Assert the response
		assertEquals(guest, response);
	}

	@Test
	void testGetGuestByIdException() {
		int guestId = 1;

		when(guestService.getGuestById(guestId)).thenThrow(new RuntimeException("Test Exception"));

		Optional<Guest> response = controller.getGuestById(guestId);

		// Assert the response
		assertNull(response);
	}

	@Test
	void testGetGuestByEmailId() {
		String emailId = "test@example.com";
		Guest guest = new Guest();

		when(guestService.getGuestByEmailId(emailId)).thenReturn(guest);

		Guest response = controller.getGuestByEmailId(emailId);

		// Assert the response
		assertEquals(guest, response);
	}

	@Test
	void testGetGuestByEmailIdException() {
		String emailId = "test@example.com";

		when(guestService.getGuestByEmailId(emailId)).thenThrow(new RuntimeException("Test Exception"));

		Guest response = controller.getGuestByEmailId(emailId);

		// Assert the response
		assertNull(response);
	}

	@Test
	void testUpdateGuest() {
		Guest guest = new Guest();
		doNothing().when(guestService).updateGuest(guest);

		ResponseEntity<?> response = controller.updateGuest(guest);

		// Assert the response
		GuestResponse expectedResponse = new GuestResponse("Updated Guest", 0, "");
		assertResponseEntityEquals(expectedResponse, response);
	}

	@Test
	void testUpdateGuestException() {
		Guest guest = new Guest();

		doThrow(new RuntimeException("Test Exception")).when(guestService).updateGuest(guest);

		ResponseEntity<?> response = controller.updateGuest(guest);

		// Assert the response
		GuestResponse expectedResponse = new GuestResponse("Problem in updating Guest", 0, "");
		assertResponseEntityEquals(expectedResponse, response);
	}
	
	

	@Test
	void testDeleteGuest() {
		int guestId = 1;
		doNothing().when(guestService).deleteGuest(guestId);

		ResponseEntity<?> response = controller.deleteGuest(guestId);

		// Assert the response
		GuestResponse expectedResponse = new GuestResponse("Deleted Guest", 0, "");
		assertResponseEntityEquals(expectedResponse, response);
	}

	@Test
	void testDeleteGuestException() {
		int guestId = 1;

		doThrow(new RuntimeException("Test Exception")).when(guestService).deleteGuest(guestId);

		ResponseEntity<?> response = controller.deleteGuest(guestId);

		// Assert the response
		GuestResponse expectedResponse = new GuestResponse("Problem in UpdatingGuest Detail", 0, "");
		assertResponseEntityEquals(expectedResponse, response);
	}

	@Test
	void testGetEmailID() {
		int guestId = 1;
		String emailId = "test@example.com";

		when(guestService.getEmailID(guestId)).thenReturn(emailId);

		String response = controller.getEmailID(guestId);

		// Assert the response
		assertEquals(emailId, response);
	}

	@Test
	void testGetEmailIDException() {
		int guestId = 1;

		when(guestService.getEmailID(guestId)).thenThrow(new RuntimeException("Test Exception"));

		String response = controller.getEmailID(guestId);

		// Assert the response
		assertEquals("Error", response);
	}

	// Helper method to compare GuestResponse in ResponseEntity
	private void assertResponseEntityEquals(GuestResponse expected, ResponseEntity<?> actual) {
		assertEquals(HttpStatus.OK, actual.getStatusCode());
		assertEquals(expected, actual.getBody());
	}

	// Helper method to compare Iterable objects
	private <T> void assertIterableEquals(Iterable<T> expected, Iterable<T> actual) {
		List<T> expectedList = new ArrayList<>();
		List<T> actualList = new ArrayList<>();

		expected.forEach(expectedList::add);
		actual.forEach(actualList::add);

		assertEquals(expectedList, actualList);
	}
}
