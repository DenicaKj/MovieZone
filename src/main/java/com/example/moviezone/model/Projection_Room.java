package com.example.moviezone.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "projection_rooms")
public class Projection_Room {
    @Id
    @Column(name = "id_room", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_room;
    Integer number_of_seats;
    Integer projection_room_number;
    @ManyToOne
    Cinema cinema;

}