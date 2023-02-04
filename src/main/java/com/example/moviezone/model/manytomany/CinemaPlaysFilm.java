package com.example.moviezone.model.manytomany;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

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
