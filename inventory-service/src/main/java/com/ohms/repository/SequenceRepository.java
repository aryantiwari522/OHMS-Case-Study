package com.ohms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ohms.model.Sequence;

/**
 * The Sequence Repository interface used to extends function from MongoRepository Class.
 */
@Repository
public interface SequenceRepository extends MongoRepository<Sequence, Integer> {
	
	Sequence findByName(String name);

}
