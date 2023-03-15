
package com.movie.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.service.entities.Movie;
import com.movie.service.entities.Theater;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, String> {

	public Movie getBymoviename(String moviename);

	public boolean deleteBymoviename(String moviename);

//	public Movie findByMoviename(String moviename);

	public Movie findBymoviename(String moviename);

//	public boolean findBymoviename(String moviename);

//	public  findBymoviename(String moviename);

}
