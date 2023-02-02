package com.example.moviezone.model.manytomany;
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
@Table(name = "`cinema_plays_film`")
=======
@Table(name = "cinema_plays_film")
>>>>>>> Stashed changes
@IdClass(CinemaPlaysFilmId.class)
public class CinemaPlaysFilm {
    @Id
    @Column(name = "id_cinema")
    Integer id_cinema;
<<<<<<< Updated upstream
    @Column(name = "id_film")
    @Id
=======

    @Id
    @Column(name = "id_film")
>>>>>>> Stashed changes
    Integer id_film;
}
