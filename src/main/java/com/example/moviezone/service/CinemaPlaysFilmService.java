package com.example.moviezone.service;

import com.example.moviezone.model.manytomany.CinemaPlaysFilm;

public interface CinemaPlaysFilmService {
    CinemaPlaysFilm save(Integer id_cinema,Integer id_film);
}
