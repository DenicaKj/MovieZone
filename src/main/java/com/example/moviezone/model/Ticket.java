package com.example.moviezone.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_ticket;

    long price;
    LocalDate date_reserved;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    Customer customer;

    public Ticket(long price, Customer customer) {
        this.price = price;
        this.customer = customer;
        this.date_reserved=LocalDate.now();
    }

    public Ticket() {

    }
}
