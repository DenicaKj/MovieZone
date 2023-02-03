package com.example.moviezone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_film;

    String name;
    Integer duration;
    String actors;
    String genre;
    String age_category;
    String url;
    String director;
    LocalDate start_date;
    LocalDate end_date;


}
