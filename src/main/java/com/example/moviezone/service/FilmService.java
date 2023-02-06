package com.example.moviezone.service;

import com.example.moviezone.model.Film;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FilmService {
    List<Film> findAllFilms();
    Film save(String name, Integer duration, String actors, String genre,
              String age_category, String url, String director, LocalDate start_date,LocalDate end_date);
    Optional<Film> getFilmById(Long id);
}
