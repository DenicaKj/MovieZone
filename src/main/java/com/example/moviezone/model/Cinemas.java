package com.example.moviezone.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Cinemas {
    @Id
    @Column(name = "id_cinema", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cinema;
    String name;
    String location;

    public Cinemas(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Cinemas() {

    }
}
