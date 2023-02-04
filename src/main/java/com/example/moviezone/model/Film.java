package com.example.moviezone.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


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

    public Film(String name, Integer duration, String actors, String genre, String age_category, String url, String director, LocalDate start_date, LocalDate end_date) {
        this.name = name;
        this.duration = duration;
        this.actors = actors;
        this.genre = genre;
        this.age_category = age_category;
        this.url = url;
        this.director = director;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Film() {

    }
}
