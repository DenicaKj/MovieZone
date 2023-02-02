package com.example.moviezone.repository;

import com.example.moviezone.model.manytomany.CinemaOrganizesEvent;
import com.example.moviezone.model.manytomany.CinemaOrganizesEventId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaOrganizesEventRepository extends JpaRepository<CinemaOrganizesEvent, CinemaOrganizesEventId> {
}
