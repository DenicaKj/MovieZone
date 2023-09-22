package com.example.moviezone.model.views;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Immutable
@Table(name = "`film_report`")
public class FilmReport {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    Integer id_film;
    Long tickets;
    BigInteger profit;
    Long customers;
    Long raitings;
}
