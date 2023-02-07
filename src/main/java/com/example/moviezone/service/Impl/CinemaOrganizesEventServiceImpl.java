package com.example.moviezone.service.Impl;

import com.example.moviezone.model.manytomany.CinemaOrganizesEvent;
import com.example.moviezone.repository.CinemaOrganizesEventRepository;
import com.example.moviezone.service.CinemaOrganizesEventService;
import org.springframework.stereotype.Service;

@Service
public class CinemaOrganizesEventServiceImpl implements CinemaOrganizesEventService {
    private final CinemaOrganizesEventRepository cinemaOrganizesEventRepository;

    public CinemaOrganizesEventServiceImpl(CinemaOrganizesEventRepository cinemaOrganizesEventRepository) {
        this.cinemaOrganizesEventRepository = cinemaOrganizesEventRepository;
    }

    @Override
    public CinemaOrganizesEvent save(Integer id_cinema, Integer id_event) {
        return cinemaOrganizesEventRepository.save(new CinemaOrganizesEvent(id_cinema,id_event));
    }
}
