package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Film;
import com.example.moviezone.repository.FilmRepository;
import com.example.moviezone.service.FilmService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
