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
@Table(name = "`customer_rates_film`")
@IdClass(CustomerRatesFilmId.class)
public class CustomerRatesFilm {

    @Id
    @Column(name ="id_customer")
    Integer id_customer;

    @Id
    @Column(name ="id_film")
    Integer id_film;

}
