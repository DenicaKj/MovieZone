package com.example.moviezone.model.manytomany;

import lombok.Data;

import java.io.Serializable;

@Data
public class CinemaOrganizesEventId implements Serializable {
    Integer id_cinema;
    Integer id_event;
}
