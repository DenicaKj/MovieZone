package com.example.moviezone.repository;

import com.example.moviezone.model.Event;
import com.example.moviezone.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Integer> {
    @Procedure("project.getEventsFromCinema")
    List<Event> getFilmsFromCinema(int id);
    @Procedure("project.getEventsFromNow")
    List<Event> getFilmsFromCinemaNow();

}
