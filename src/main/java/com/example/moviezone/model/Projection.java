package com.example.moviezone.model;


import jakarta.persistence.*;
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
    Film film;
    @ManyToOne
    Event event;

}
