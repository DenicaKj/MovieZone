package com.example.moviezone.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_event;

    String theme;
    String duration;
    String repeating;

    LocalDate start_date;

    public Event(String theme, String duration, String repeating, LocalDate start_date) {
        this.theme = theme;
        this.duration = duration;
        this.repeating = repeating;
        this.start_date = start_date;
    }

    public Event() {

    }
}
