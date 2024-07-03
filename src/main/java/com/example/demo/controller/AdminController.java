package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableMethodSecurity
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/home")
	public void Display() {
		System.out.print("THis is Admin Home");
	}

}
