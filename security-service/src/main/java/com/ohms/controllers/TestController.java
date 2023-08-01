package com.ohms.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/reception")
	@PreAuthorize("hasRole('RECEPTION') or hasRole('MANAGER') or hasRole('OWNER')")
	public String userAccess() {
		return "RECEPTION Content.";
	}

	@GetMapping("/manager")
	@PreAuthorize("hasRole('MANAGER')")
	public String moderatorAccess() {
		return "MANAGER Board.";
	}

	@GetMapping("/owner")
	@PreAuthorize("hasRole('OWNER')")
	public String adminAccess() {
		return "OWNER Board.";
	}
}
