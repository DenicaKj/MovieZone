package com.example.moviezone.model;


import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "projections")
public class Projection {
    @Id
    @Column(name = "id_projection", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_projection;

    LocalDate date_time_start;
    String type_of_technology;
    LocalDate date_time_end;
    @ManyToOne
    @JoinColumn(name = "id_film")
    Film film;
    @ManyToOne
    @JoinColumn(name = "id_event")
    Event event;
    @ManyToOne
    @JoinColumn(name = "id_discount")
    Discount discount;

    public Projection(LocalDate date_time_start, String type_of_technology, LocalDate date_time_end, Film film) {
        this.date_time_start = date_time_start;
        this.type_of_technology = type_of_technology;
        this.date_time_end = date_time_end;
        this.film = film;
    }

    public Projection() {
    }
}
