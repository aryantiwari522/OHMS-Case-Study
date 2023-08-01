package com.ohms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is Response class which is used to send the response of any api call
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
	private String message;
}
