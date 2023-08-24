package com.example.moviezone.service;

import com.example.moviezone.model.Film;
import com.example.moviezone.model.Projection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ProjectionService {
    List<Projection> findAllProjections();
    List<Projection> getProjectionsForFilms(int id);
    List<Projection> getProjectionsNow();
    Projection findById(Integer id_projection);
    Projection save(LocalDateTime date_time_start, LocalDateTime date_time_end, String type_of_technology, Integer id_film, Integer id_room, Integer id_discount);
}
