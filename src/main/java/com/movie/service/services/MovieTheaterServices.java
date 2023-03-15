package com.movie.service.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movie.service.dto.BookingIdDto;
import com.movie.service.dto.GetPrice;
import com.movie.service.dto.MovieDto;
import com.movie.service.dto.MovieTheaterdto;
import com.movie.service.dto.TheaterDto;
import com.movie.service.entities.Movie;
import com.movie.service.entities.Theater;

@Service
public interface MovieTheaterServices {

	public String saveMovieDetails(MovieDto moviedto);

	public String saveTheaterDetails(TheaterDto theaterdto);

	public void saveMovie(Movie movie);

	public List<MovieDto> getAllMovies();

	public List<TheaterDto> getAllTheaters();

	public String assignMovietoTheater(String moviename, String theatername);

	public String deleteMovie(String moviename);

	public List<TheaterDto> deleteTheater(String theatername);

	public BookingIdDto checkingDetails(MovieTheaterdto movietheaterdto);

	public MovieDto findbymoviename(String moviename);

	public List<Theater> showAllTheaters();

	public GetPrice getprice(String theatername);

	public MovieDto getmovie(String moviename);

}
