package com.ohms.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin(origins = "http://localhost:4200")
public class ApiGatewayConfiguration {
	@Bean
	public RouteLocator routeLocatorGuest(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p->p
						.path("/guest/get")
						.uri("lb://guest-service/")
						)
				.route(p->p
						.path("/guest/add")
						.uri("lb://guest-service/")
						)
				.route(p->p
						.path("/guest/get/{id}")
						.uri("lb://guest-service/")
						)
				.route(p->p
						.path("/guest/update")
						.uri("lb://guest-service/")
						)
				.route(p->p
						.path("/guest/delete/{id}")
						.uri("lb://guest-service/")
						)
				.route(p->p
						.path("/guest/getByEmail/{emailId}")
						.uri("lb://guest-service/")
				)
				.build();		
	}

	@Bean
	public RouteLocator routeLocatorRoom(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p->p
						.path("/room/get")
						.uri("lb://room-service/")
				)
				.route(p->p
						.path("/room/add")
						.uri("lb://room-service/")
				)
				.route(p->p
						.path("/room/get/{roomId}")
						.uri("lb://room-service/")
				)
				.route(p->p
						.path("/room/update")
						.uri("lb://room-service/")
				)
				.route(p->p
						.path("/room/delete/{roomId}")
						.uri("lb://room-service/")
				)
				.route(p->p
						.path("/room/getbystatus/{status}")
						.uri("lb://room-service/")
				)
				.build();
	}

	@Bean
	public RouteLocator routeLocatorBooking(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p->p
						.path("/booking/get")
						.uri("lb://booking-service/")
				)
				.route(p->p
						.path("/booking/add")
						.uri("lb://booking-service/")
				)
				.route(p->p
						.path("/booking/payment/{bookingId}")
						.uri("lb://booking-service/")
				)
				.route(p->p
						.path("/booking/cancel/{bookingId}")
						.uri("lb://booking-service/")
				)
				.route(p->p
						.path("/booking/get/{bookingId}")
						.uri("lb://booking-service/")
				)
				.route(p->p
						.path("/booking/getbycheckindate/{checkInDate}")
						.uri("lb://booking-service/")
				)
				.build();
	}

	@Bean
	public RouteLocator routeLocatorInventory(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p->p
						.path("/inventory/get")
						.uri("lb://inventory-service/")
				)
				.route(p->p
						.path("/inventory/add")
						.uri("lb://inventory-service/")
				)
				.route(p->p
						.path("/inventory/get/{productId}")
						.uri("lb://inventory-service/")
				)
				.route(p->p
						.path("/inventory/update/{id}")
						.uri("lb://inventory-service/")
				)
				.route(p->p
						.path("/inventory/delete/{productId}")
						.uri("lb://inventory-service/")
				)
				.build();
	}

}
