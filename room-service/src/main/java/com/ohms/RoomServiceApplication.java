package com.ohms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * This is a Room Microservice which is used to manage and fetch the Room details
 *
 */

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="Room API",version="1.0",description="Room microservice"))
public class RoomServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomServiceApplication.class, args);
	}

}
