package com.ohms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohms.model.Sequence;
import com.ohms.repository.SequenceRepository;

/**
 *This  class is track of sequence for auto generated id
 */
@Service
public class SequenceService {
	
	@Autowired
	private SequenceRepository sequenceRepository;
	
	//Return the next sequence
	public int getNextSequence(String seqenceName) {
		Sequence sequence = sequenceRepository.findByName(seqenceName);
		if(sequence == null) {
			sequence = new Sequence(126, "employee", 0);
			sequenceRepository.save(sequence);
		}
		int nextSeq = sequence.getSeq() ;
		nextSeq ++;
		sequence.setSeq(nextSeq);
		sequenceRepository.save(sequence);
		return nextSeq;
	}
}
