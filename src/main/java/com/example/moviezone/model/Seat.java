package com.example.moviezone.model;

import javax.persistence.*;
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
    @JoinColumn(name = "id_room")
    Projection_Room projection;
    @ManyToOne
    @JoinColumn(name = "id_category")
    Category category;

}
