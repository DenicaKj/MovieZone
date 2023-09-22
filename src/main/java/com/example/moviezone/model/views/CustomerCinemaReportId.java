package com.example.moviezone.model.views;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerCinemaReportId implements Serializable {
    Integer id_customer;
    Integer id_cinema;
}
