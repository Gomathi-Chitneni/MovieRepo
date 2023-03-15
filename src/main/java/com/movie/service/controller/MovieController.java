package com.movie.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.movie.service.dto.BookingIdDto;
import com.movie.service.dto.GetPrice;
import com.movie.service.dto.MovieDto;
import com.movie.service.dto.MovieTheaterdto;
import com.movie.service.dto.TheaterDto;
import com.movie.service.entities.Movie;
import com.movie.service.exception.BussinessException;

import com.movie.service.repository.MovieRepository;
import com.movie.service.repository.TheaterRepository;
import com.movie.service.services.ImpMovieTheaterServcies;
import com.movie.service.services.MovieTheaterServices;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieTheaterServices moviestheaterervices;

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/home")
	public ResponseEntity<String> moviepage() {

		return ResponseEntity.ok("Welcome to MovieBooking page");

	}

	@PostMapping("/saveMovie")
	public ResponseEntity<?> saveMovieDetails(@RequestBody MovieDto moviedto) {
		moviestheaterervices.saveMovieDetails(moviedto);
		return new ResponseEntity<String>("Moviedetials created", HttpStatus.CREATED);

	}

	@PostMapping("/save")
	public ResponseEntity<String> saveMovie(@RequestBody Movie movie) {
		moviestheaterervices.saveMovie(movie);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Movie created");
	}

	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@GetMapping("showMovies")
	public ResponseEntity<List<MovieDto>> getAllMovies() {
		List<MovieDto> moviedto = moviestheaterervices.getAllMovies();
		return ResponseEntity.ok(moviedto);
	}

	@ResponseStatus(value = HttpStatus.FOUND)
	@GetMapping("/getmovies/{moviename}")
	public ResponseEntity<MovieDto> getmovie(@PathVariable String moviename) {
		MovieDto moviedto = moviestheaterervices.getmovie(moviename);
		return ResponseEntity.ok(moviedto);
	}

	@PutMapping("/{moviename}/match/{theatername}")
	public ResponseEntity<String> assignMovietoTheater(@PathVariable String moviename,
			@PathVariable String theatername) {
		moviestheaterervices.assignMovietoTheater(moviename, theatername);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Assigned Movies to theaters");
	}

	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping("/delete/{moviename}")
	public ResponseEntity<String> deleteMovie(@PathVariable String moviename) {
		String str = moviestheaterervices.deleteMovie(moviename);
		return ResponseEntity.ok(str);

	}

	@PostMapping("/checkingdetails")
	public ResponseEntity<BookingIdDto> checkingDetails(@RequestBody MovieTheaterdto movietheaterdto) {
		BookingIdDto bookingIdDto = moviestheaterervices.checkingDetails(movietheaterdto);
		return new ResponseEntity<>(HttpStatus.ACCEPTED).ok(bookingIdDto);

	}

	@PutMapping("/getprice")
	public GetPrice getprice(@RequestBody String theatername) {
		return moviestheaterervices.getprice(theatername);
	}

	@GetMapping("/api/moviename")
	public String getid(@RequestParam String moviename) {
		return "book" + moviename;
	}

}
