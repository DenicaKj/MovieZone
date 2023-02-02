package com.example.moviezone.repository;

import com.example.moviezone.model.manytomany.CustomerRatesFilm;
import com.example.moviezone.model.manytomany.CustomerRatesFilmId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRatesFilmRepository extends JpaRepository<CustomerRatesFilm, CustomerRatesFilmId> {
}
