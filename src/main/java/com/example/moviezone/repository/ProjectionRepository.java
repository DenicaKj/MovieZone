package com.example.moviezone.repository;

import com.example.moviezone.model.Projection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProjectionRepository extends JpaRepository<Projection,Integer> {
    //    NOTE: CHANGE THIS WITH MATERIALIZED VIEW
    //List<Projection> findAllBydate_time_startBefore(LocalDate datum);
}
