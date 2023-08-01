package com.ohms.notification;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageClass implements Serializable {
	
	private String guestEmail;
	private String subject;
	private String body;

}
