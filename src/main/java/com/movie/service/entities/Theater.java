package com.movie.service.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
public class Theater {

	@Id
	@Column(length = 50)
	private String theatername;

	private int availableseats;

	@ManyToMany
	@JoinTable(name = "theater_movie", joinColumns = @JoinColumn(name = "theatername"), inverseJoinColumns = @JoinColumn(name = "moviename"))
	List<Movie> assignedmovies;

	public String getTheatername() {
		return theatername;
	}

	public void setTheatername(String theatername) {
		this.theatername = theatername;
	}

	public int getAvailableseats() {
		return availableseats;
	}

	public void setAvailableseats(int availableseats) {
		this.availableseats = availableseats;
	}

	public List<Movie> getAssignedmovies() {
		return assignedmovies;
	}

	public void setAssignedmovies(List<Movie> assignedmovies) {
		this.assignedmovies = assignedmovies;
	}

	@Override
	public String toString() {
		return "Theater [theatername=" + theatername + ", availableseats=" + availableseats + ", assignedmovies="
				+ assignedmovies + "]";
	}

}
