package com.example.moviezone.service;

import com.example.moviezone.model.Projection;

import java.time.LocalDate;
import java.util.List;

public interface ProjectionService {
    List<Projection> findAllProjections();
List<Projection> findAllAvailableProjections(LocalDate date);

}
