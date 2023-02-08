package com.example.moviezone.service;

import com.example.moviezone.model.Film;
import com.example.moviezone.model.Projection;

import java.time.LocalDate;
import java.util.List;

public interface ProjectionService {
    List<Projection> findAllProjections();
    List<Projection> getProjectionsForFilms(int id);
    Projection findById(Integer id_projection);
Projection save(LocalDate date_time_start,LocalDate date_time_end, String type_of_technology, Integer id_film );
}
