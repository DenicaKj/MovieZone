package com.example.moviezone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "seats")
public class Seat {
    @Id
    @Column(name = "id_seat", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_seat;
    Integer seat_number;
    @ManyToOne
    Projection_Room projection_room;
    @ManyToOne
    Category category;

}
