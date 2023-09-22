package com.example.moviezone.model.views;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Immutable
@Table(name = "`customer_cinema_report`")
@IdClass(CustomerCinemaReportId.class)
public class CustomerCinemaReport implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    Integer id_customer;
    @Id
    Integer id_cinema;
    Long tickets;
    BigInteger sum;
    Long discounts;
}
