package com.example.moviezone.service.Impl;

import com.example.moviezone.model.manytomany.CustomerRatesFilm;
import com.example.moviezone.repository.CustomerRatesFilmRepository;
import com.example.moviezone.service.CustomerRatesFilmService;
import org.springframework.stereotype.Service;

@Service
public class CustomerRatesFilmImpl implements CustomerRatesFilmService {
    private final CustomerRatesFilmRepository customerRatesFilmRepository;

    public CustomerRatesFilmImpl(CustomerRatesFilmRepository customerRatesFilmRepository) {
        this.customerRatesFilmRepository = customerRatesFilmRepository;
    }

    @Override
    public double avg_rating(int id) {
        return customerRatesFilmRepository.avg_rating(id);
    }

    @Override
    public CustomerRatesFilm addRating(Integer id_customer, Integer id_film, double rating) {
        CustomerRatesFilm customerRatesFilm=new CustomerRatesFilm(id_customer,id_film,rating);
        return customerRatesFilmRepository.save(customerRatesFilm);
    }
}
