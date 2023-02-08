package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Film;
import com.example.moviezone.model.procedures.FilmsReturnTable;
import com.example.moviezone.repository.FilmRepository;
import com.example.moviezone.service.FilmService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> findAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Film save(String name, Integer duration, String actors, String genre, String age_category, String url, String director, LocalDate start_date, LocalDate end_date) {
        return filmRepository.save(new Film(name,duration,actors,genre,age_category,url,director,start_date,end_date));
    }

    @Override
    public Optional<Film> getFilmById(Long id) {
        return filmRepository.findAllById(Collections.singleton(id.intValue())).stream().findFirst();
    }

    @Override
    public List<Film> getFilmsFromCinema(int id) {
        return filmRepository.getFilmsFromCinema(id);
    }

}
