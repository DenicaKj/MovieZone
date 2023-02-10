package com.example.moviezone.service;

import com.example.moviezone.model.Customer;
import com.example.moviezone.model.manytomany.CustomerRatesFilm;

public interface CustomerRatesFilmService {
    double avg_rating(int id);
    CustomerRatesFilm addRating(Integer id_customer, Integer id_film, double rating);

}
