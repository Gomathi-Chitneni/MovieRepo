
package com.movie.service.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Movie")
public class Movie {

	@Id
	@Column(length = 50)
	private String moviename;

	@Lob
	@Column(name = "CAST")
	private String castString;
	private transient Cast cast;

	public String getCastString() {
		return castString;
	}

	public void setCastString(String castString) {
		this.castString = castString;
	}

	private String type;

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	@Transient
	public Cast getCast() {
		return cast;
	}

	@Transient
	public void setCast(Cast cast) {
		this.cast = cast;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
