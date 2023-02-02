package com.example.moviezone.model.manytomany;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

@Table(name = "`cinema_plays_film`")

@IdClass(CinemaPlaysFilmId.class)
public class CinemaPlaysFilm {
    @Id
    @Column(name = "id_cinema")
    Integer id_cinema;
    


    @Id
    @Column(name = "id_film")
    Integer id_film;
}
