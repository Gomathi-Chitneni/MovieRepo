package com.movie.service.dto;

import com.movie.service.entities.Cast;

public class MovieDto {

	private String moviename;
	private String type;
	private Cast cast;
	
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Cast getCast() {
		return cast;
	}
	public void setCast(Cast cast) {
		this.cast = cast;
	}
	
	
}
