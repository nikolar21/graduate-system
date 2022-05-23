package com.tusofia.graduatesystem.controller;

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
	
	@GetMapping("/student")
	@PreAuthorize("hasRole('STUDENT') or hasRole('MENTOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "Student Board.";
	}

	@GetMapping("/mentor")
	@PreAuthorize("hasRole('MENTOR')")
	public String moderatorAccess() {
		return "Mentor Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
