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
@Table(name = "`cinema_organizes_event`")
@IdClass(CinemaOrganizesEventId.class)
public class CinemaOrganizesEvent {
    @Id
    @Column(name = "id_cinema")
    Integer id_cinema;
    @Column(name = "id_event")
    @Id
    Integer id_event;
}
