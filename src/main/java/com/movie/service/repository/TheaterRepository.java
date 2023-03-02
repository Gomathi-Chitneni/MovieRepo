package com.movie.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.service.entities.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, String> {

}
