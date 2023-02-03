package com.example.moviezone.repository;

import com.example.moviezone.model.manytomany.CinemaPlaysFilm;
import com.example.moviezone.model.manytomany.CinemaPlaysFilmId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaPlaysFilmRepository extends JpaRepository<CinemaPlaysFilm, CinemaPlaysFilmId> {
}
