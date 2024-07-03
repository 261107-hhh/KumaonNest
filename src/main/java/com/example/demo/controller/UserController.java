package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class  UserController {

	@GetMapping("/home")
	public void Display() {
		System.out.print("THis is User Home");
	}
	
}
