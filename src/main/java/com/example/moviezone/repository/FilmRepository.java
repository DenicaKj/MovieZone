package com.example.moviezone.repository;

import com.example.moviezone.model.Film;
import com.example.moviezone.model.procedures.FilmsReturnTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film,Integer> {
    @Procedure("project.getFilmsFromCinema")
    List<FilmsReturnTable> getFilmsFromCinema(int id);
}
