package com.example.moviezone.model;


import javax.persistence.*;
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
    Integer id_room;
    Integer number_of_seats;
    Integer projection_room_number;
    @ManyToOne
    @JoinColumn(name = "id_cinema")
    Cinema cinema;

}
