package com.ohms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohms.model.Guest;
import com.ohms.repository.GuestRepository;

/**
 * This Class contains the business logics for the guest service.
 *
 */

@Service
public class GuestService {
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private SequenceService sequenceService;

	// Add new Guest to database
	public void addGuest(Guest guest) {
		int getId = sequenceService.getNextSequence("guest");
		guest.setGuestId(getId);
		guestRepository.save(guest);
	}
	
	// Return list of all Guest 
	public List<Guest> getAllGuests() {
		return guestRepository.findAll();
	}
	
	// Return Guest detail by id if present else null
	public Optional<Guest> getGuestById(int guestId) {
		return guestRepository.findById(guestId);
	}
	
	// Return Guest detail by emailId if present else null
	public Guest getGuestByEmailId(String emailId) {
		return guestRepository.findByGuestEmailId(emailId);
	}
	
	// Takes Guest detail as input and save the detail in database/repository
	public void updateGuest(Guest guest) {
		guestRepository.save(guest);
	}

	//Delete the Guest detail by id
	public void deleteGuest(int guestId) {
		guestRepository.deleteById(guestId);
	}
	
	// Return the guest email id for requested id
	public String getEmailID(int guestId) {
		Guest guest = guestRepository.findById(guestId).get();	
		return guest.getGuestEmailId();
	}
	
	// used to check if guest is existed by id or not
	public boolean isExistedById(int guestId) {
		return guestRepository.existsByGuestId(guestId);
	}
	
	// used to check if guest is existed by EmailId or not
	public boolean isExistedByEmailId(String guestEmailId) {
		return guestRepository.existsByGuestEmailId(guestEmailId);
	}
}
