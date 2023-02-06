package com.example.moviezone.repository;

import com.example.moviezone.model.manytomany.CustomerRatesFilm;
import com.example.moviezone.model.manytomany.CustomerRatesFilmId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRatesFilmRepository extends JpaRepository<CustomerRatesFilm, CustomerRatesFilmId> {
@Procedure("project.avg_rating1")
    double avg_rating(int id);

}
