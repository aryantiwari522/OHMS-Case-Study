package com.ohms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohms.model.Sequence;
import com.ohms.repository.SequenceRepository;

@Service
public class SequenceService {
	
	@Autowired
	private SequenceRepository sequenceRepository;

	public void add(Sequence sequence) {
		sequenceRepository.save(sequence);
	}
	
	public int getNextSequence(String seqenceName) {
		Sequence sequence = sequenceRepository.findByName(seqenceName);
		if(sequence == null) {
			sequence = new Sequence(123, "booking", 0);
			sequenceRepository.save(sequence);
		}
		int nextSeq = sequence.getSeq() ;
		nextSeq ++;
		sequence.setSeq(nextSeq);
		sequenceRepository.save(sequence);
		return nextSeq;
	}
}
