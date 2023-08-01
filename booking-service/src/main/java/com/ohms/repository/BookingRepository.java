package com.ohms.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ohms.model.Booking;

@Repository
public interface BookingRepository extends MongoRepository<Booking, Integer> {

	Booking findByRoomIdAndCheckInDate(String roomId, Date checkInDate);
	List<Booking> findByCheckInDate(Date checkInDate);
}
