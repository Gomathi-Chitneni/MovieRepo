package com.movie.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.movie.service.dto.MovieDto;
import com.movie.service.dto.MovieTheaterdto;
import com.movie.service.dto.TheaterDto;
import com.movie.service.entities.Theater;
import com.movie.service.services.MovieTheaterServices;

@RestController
@RequestMapping("/theater")
public class TheaterController {

	@Autowired
	private MovieTheaterServices movietheaterservices;

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/home")
	public ResponseEntity<String> homepage() {
		return ResponseEntity.ok("Welcome to Theaterpage");
	}

	@PostMapping("/saveTheaters")
	public ResponseEntity<String> saveTheaterDetials(@RequestBody TheaterDto theaterdto) {
		movietheaterservices.saveTheaterDetails(theaterdto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Saved Theater Detials");

	}

	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@GetMapping("/showTheaters")
	public ResponseEntity<List<TheaterDto>> getAllTheaters() {
		List<TheaterDto> theaterdto = movietheaterservices.getAllTheaters();
		return ResponseEntity.ok(theaterdto);
	}

	@GetMapping("/moviename/{moviename}")
	public ResponseEntity<MovieDto> findbymoviename(@PathVariable String moviename) {
		MovieDto movieDto = movietheaterservices.findbymoviename(moviename);
		return new ResponseEntity<MovieDto>(movieDto, HttpStatus.FOUND);

	}

	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping("/delete/{theatername}")
	public ResponseEntity<List<TheaterDto>> deleteTheater(@PathVariable String theatername) {
		List<TheaterDto> theaterdto = movietheaterservices.deleteTheater(theatername);
		return ResponseEntity.ok(theaterdto);
	}

	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@GetMapping("/showtheater")
	public ResponseEntity<List<Theater>> showAllTheaters() {
		List<Theater> theater = movietheaterservices.showAllTheaters();
		return ResponseEntity.ok(theater);
	}

}
