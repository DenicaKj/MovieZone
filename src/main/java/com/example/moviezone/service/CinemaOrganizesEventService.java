package com.example.moviezone.service;

import com.example.moviezone.model.manytomany.CinemaOrganizesEvent;

public interface CinemaOrganizesEventService {
    CinemaOrganizesEvent save(Integer id_cinema, Integer id_event);
}
