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

import com.ohms.model.Guest;
import com.ohms.repository.GuestRepository;
import com.ohms.service.GuestService;

@SpringBootTest
class GuestServiceApplicationTests {
	
	@Autowired
	private GuestService guestService;
	
	@MockBean
	private GuestRepository guestRepository;
	
	@Test
	void addGuestTest() {
		Guest guest=new Guest(123, "Sanjay Kumar", 21, 700563458, "abhishek123@gmail.com", "Lucknow");
		guestService.addGuest(guest);
		verify(guestRepository,times(1)).save(guest);
	}
	
	@Test
	void getAllGuestsTest() {
		when(guestRepository.findAll()).thenReturn(Stream
				.of(new Guest(123, "Sanjay Kumar", 21, 700563458, "sanjay123@gmail.com", "Lucknow"),
					new Guest(124, "Abhikshek Kumar", 22, 800763458, "abhishek123@gmail.com", "Delhi"))
				.collect(Collectors.toList()));
		assertEquals(2, guestService.getAllGuests().size());
	}
	
//	@Test
//	void getGuestByIdTest() {
//		when(guestRepository.findById(123)).thenReturn(
//		new Optional<Guest>(new Guest(123, "Sanjay Kumar", 21, 700563458, "sanjay123@gmail.com", "Lucknow"));
//	}
	
	@Test
	void getGuestByEmailIdTest() {
		when(guestRepository.findByGuestEmailId("sanjay123@gmail.com")).thenReturn(
				new Guest(123, "Sanjay Kumar", 21, 700563458, "sanjay123@gmail.com", "Lucknow"));
		Guest guest = new Guest(123, "Sanjay Kumar", 21, 700563458, "sanjay123@gmail.com", "Lucknow");
		assertEquals(guest.getClass(), guestService.getGuestByEmailId("sanjay123@gmail.com").getClass());
	}

	@Test
	void updateGuestTest() {
		Guest guest=new Guest(123, "Sanjay Kumar", 21, 700563458, "sanjay123@gmail.com", "Lucknow");
		guestService.updateGuest(guest);
		verify(guestRepository,times(1)).save(guest);
	}
	@Test
	void deleteGuestTest() {
		int gId=123;
		guestService.deleteGuest(gId);
		verify(guestRepository,times(1)).deleteById(gId);
	}

}
