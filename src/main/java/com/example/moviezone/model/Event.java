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
    String img_url;
    LocalDate start_date;

    public Event(String theme, String duration, String repeating, LocalDate start_date,String img_url) {
        this.theme = theme;
        this.duration = duration;
        this.img_url=img_url;
        this.repeating = repeating;
        this.start_date = start_date;
    }

    public Event() {

    }

}
