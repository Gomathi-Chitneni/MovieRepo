package com.testcases.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.movie.service.controller.MovieController;
import com.movie.service.dto.MovieDto;
import com.movie.service.services.MovieTheaterServices;

class TestMovie {

	@InjectMocks
	private MovieController movieController;
	
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

	
}
