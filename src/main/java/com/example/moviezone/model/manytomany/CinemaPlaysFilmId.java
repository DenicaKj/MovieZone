package com.example.moviezone.model.manytomany;

import lombok.Data;

import java.io.Serializable;

@Data
public class CinemaPlaysFilmId implements Serializable {
    Integer id_cinema;
    Integer id_film;
}
