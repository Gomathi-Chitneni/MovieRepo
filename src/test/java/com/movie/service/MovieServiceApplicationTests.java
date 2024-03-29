package com.movie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.movie.service.controller.MovieController;
import com.movie.service.controller.TheaterController;
import com.movie.service.dto.MovieDto;
import com.movie.service.dto.TheaterDto;
import com.movie.service.services.MovieTheaterServices;

@SpringBootTest
class MovieServiceApplicationTests {
	
	@InjectMocks
	private MovieController movieController;
	
	@InjectMocks
	private TheaterController theaterController;
	
	@Mock
	private MovieTheaterServices moviestheaterervices;
	
	@Test
	public void testSaveMovieDetails()
	{
		MovieDto moviedto=new MovieDto();
		moviedto.setCast(null);
		moviedto.setMoviename("SIR");
		moviedto.setType("DRAMA");
		when(moviestheaterervices.saveMovieDetails(any())).thenReturn("saved");
		movieController.saveMovieDetails(moviedto);
		
		assertEquals("Moviedetials created", movieController.saveMovieDetails(moviedto).getBody());
		
	}
	
	@Test
	public void testsaveTheaterDetials()
	{
		TheaterDto theaterdto=new TheaterDto();
		theaterdto.setTheatername("marina mall");
		theaterdto.setPrice(340);
		when(moviestheaterervices.saveTheaterDetails(any())).thenReturn("saved");
		theaterController.saveTheaterDetials(theaterdto);
		assertEquals("Saved Theater Detials", theaterController.saveTheaterDetials(theaterdto).getBody());
	}
	
	



}
