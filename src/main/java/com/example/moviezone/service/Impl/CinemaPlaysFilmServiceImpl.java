package com.example.moviezone.service.Impl;

import com.example.moviezone.model.manytomany.CinemaPlaysFilm;
import com.example.moviezone.repository.CinemaPlaysFilmRepository;
import com.example.moviezone.service.CinemaPlaysFilmService;
import org.springframework.stereotype.Service;

@Service
public class CinemaPlaysFilmServiceImpl implements CinemaPlaysFilmService {
    private  final CinemaPlaysFilmRepository cinemaPlaysFilmRepository;

    public CinemaPlaysFilmServiceImpl(CinemaPlaysFilmRepository cinemaPlaysFilmRepository) {
        this.cinemaPlaysFilmRepository = cinemaPlaysFilmRepository;
    }

    @Override
    public CinemaPlaysFilm save(Integer id_cinema, Integer id_film) {
        return cinemaPlaysFilmRepository.save(new CinemaPlaysFilm(id_cinema,id_film));
    }
}
