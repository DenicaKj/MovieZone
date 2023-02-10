package com.example.moviezone.model.manytomany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

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

    double rating;

    public CustomerRatesFilm(Integer id_customer, Integer id_film, double rating) {
        this.id_customer = id_customer;
        this.id_film = id_film;
        this.rating = rating;
    }
}
