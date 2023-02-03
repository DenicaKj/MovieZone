package com.example.moviezone.repository;

import com.example.moviezone.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CinemaRepository extends JpaRepository<Cinema,Integer> {
}
