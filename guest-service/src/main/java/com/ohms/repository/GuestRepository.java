package com.ohms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ohms.model.Guest;



/**
 * The Guest Repository interface used to extends function from MongoRepository Class.
 *
 */
@Repository
public interface GuestRepository extends MongoRepository<Guest, Integer> {

	Guest findByGuestEmailId(String guestEmailId);
	boolean existsByGuestId(int guestId);
	boolean existsByGuestEmailId(String guestEmailId);
}
