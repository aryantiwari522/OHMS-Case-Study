package com.ohms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the response sent when guest is added
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestResponse {	
	private String message;
	private int guestId;
	private String guestEmailId;
}
