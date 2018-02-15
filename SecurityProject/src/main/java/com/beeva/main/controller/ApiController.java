package com.beeva.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
	
	@GetMapping
	public String getInformation(){
		return "This only a little test about filter security";
	}
}
