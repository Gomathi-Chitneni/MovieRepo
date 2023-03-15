package com.movie.service.dto;

import java.util.ArrayList;
import java.util.List;

import com.movie.service.entities.Movie;

public class TheaterDto {

	private String theatername;
	private float price;
	private List<MovieDto> assignedmovies;

	public String getTheatername() {
		return theatername;
	}

	public void setTheatername(String theatername) {
		this.theatername = theatername;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<MovieDto> getAssignedmovies() {
		return assignedmovies;
	}

	public void setAssignedmovies(List<MovieDto> assignedmovies) {
		this.assignedmovies = assignedmovies;
	}

}
