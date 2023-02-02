package com.example.moviezone.repository;

import com.example.moviezone.model.manytomany.CinemaPlaysFilm;
import com.example.moviezone.model.manytomany.CinemaPlaysFilmId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaPlaysFilmRepository extends JpaRepository<CinemaPlaysFilm, CinemaPlaysFilmId> {
}
