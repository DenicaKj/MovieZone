package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Event;
import com.example.moviezone.repository.EventRepository;
import com.example.moviezone.service.EventService;
import org.springframework.stereotype.Service;

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
}
