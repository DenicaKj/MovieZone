package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Event;
import com.example.moviezone.repository.EventRepository;
import com.example.moviezone.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event save(LocalDate start_date, String theme, String duration, String repeating,String img_url) {
        return eventRepository.save(new Event(theme,duration,repeating,start_date,img_url));
    }

    @Override
    public List<Event> getEventsNow() {
        return eventRepository.getFilmsFromCinemaNow();
    }

    @Override
    public List<Event> getEventsFromCinema(int id) {
        return eventRepository.getFilmsFromCinema(id);
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findAllById(Collections.singleton(id.intValue())).stream().findFirst();
    }

    @Override
    public List<Event> getEventsForCustomer(int id) {
        return eventRepository.getEventsForCustomer(id);
    }
}
