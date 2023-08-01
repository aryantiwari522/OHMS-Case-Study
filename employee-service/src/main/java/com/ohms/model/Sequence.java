package com.ohms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class to store the data for auto generated id
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("sequence")
public class Sequence {

	@Id
	private int id;
	private String name;
	private int seq;
}
