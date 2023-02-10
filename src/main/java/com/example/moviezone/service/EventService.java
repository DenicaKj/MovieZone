package com.example.moviezone.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.moviezone.model.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAllEvents();
    Event save(LocalDate start_date,String theme,String duration,String repeating,String url);
    List<Event> getEventsNow();
    List<Event> getEventsFromCinema(int id);
    Optional<Event> getEventById(Long id);
    List<Event> getEventsForCustomer(int id);
}
