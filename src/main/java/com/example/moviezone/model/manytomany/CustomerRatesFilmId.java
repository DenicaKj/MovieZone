package com.example.moviezone.model.manytomany;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerRatesFilmId implements Serializable {
    Integer id_customer;
    Integer id_film;
}
