package com.movie.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.service.entities.Movie;
import com.movie.service.entities.Theater;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface TheaterRepository extends JpaRepository<Theater, String> {

	public Theater getBytheatername(String theartername);

	public void deleteBytheatername(String theatername);

//	public boolean findByMoviename(String moviename);

	public Theater findByTheatername(String theatername);

//	public boolean findByAvailableseatsGreaterThan(int availableseats);

//	public void findBytheatername(String theatername);

}
