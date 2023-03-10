package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Cinema;
import com.example.moviezone.repository.CinemaRepository;
import com.example.moviezone.service.CinemaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {
    private final CinemaRepository cinemaRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public List<Cinema> findAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public Cinema findCinemaById(Integer id_cinema) {
        return cinemaRepository.findById(id_cinema).orElseThrow(RuntimeException::new);
    }
}
