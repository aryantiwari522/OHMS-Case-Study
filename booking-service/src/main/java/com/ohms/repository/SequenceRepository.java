package com.ohms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ohms.model.Sequence;

@Repository
public interface SequenceRepository extends MongoRepository<Sequence, Integer> {
	
	Sequence findByName(String name);

}
