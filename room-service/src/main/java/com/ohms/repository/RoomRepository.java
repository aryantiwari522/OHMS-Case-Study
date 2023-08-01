package com.ohms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ohms.model.Room;

/**
 * The Room Repository interface used to extends function from MongoRepository Class.
 *
 */

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
	
	List<Room> findByStatus(boolean status);
}
