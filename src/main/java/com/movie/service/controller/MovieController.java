package com.movie.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@GetMapping("/home")
	public ResponseEntity<String> moviepage()
	{
		return ResponseEntity.ok("Welcome to MovieBooking page");
	}
	
	public ResponseEntity<String> setmovie()
	{
		
	}
}

