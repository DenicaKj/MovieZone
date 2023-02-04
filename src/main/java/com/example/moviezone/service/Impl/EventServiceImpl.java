package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Event;
import com.example.moviezone.repository.EventRepository;
import com.example.moviezone.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
    public Event save(LocalDate start_date, String theme, String duration, String repeating) {
        return eventRepository.save(new Event(theme,duration,repeating,start_date));
    }
}
