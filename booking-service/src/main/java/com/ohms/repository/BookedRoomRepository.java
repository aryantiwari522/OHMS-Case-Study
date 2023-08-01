package com.ohms.repository;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ohms.model.BookedRooms;

@Repository
public interface BookedRoomRepository extends MongoRepository<BookedRooms, Date> {

	BookedRooms findByDate(Date date);
	boolean existsByDate(Date date);
}
