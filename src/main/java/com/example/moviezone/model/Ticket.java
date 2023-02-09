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
    @ManyToOne
    @JoinColumn(name = "id_projection")
    Projection projection;
    @ManyToOne
    @JoinColumn(name = "id_discount")
    Discount discount;
    @ManyToOne
    @JoinColumn(name = "id_seat")
    Seat seat;

    public Ticket(long price, Customer customer) {
        this.price = price;
        this.customer = customer;
        this.date_reserved=LocalDate.now();
    }

    public Ticket(LocalDate date_reserved, Customer customer, Projection projection, Seat seat) {
        this.date_reserved = date_reserved;
        this.customer = customer;
        this.projection = projection;
        this.seat = seat;
    }

    public Ticket( LocalDate date_reserved, Customer customer, Projection projection, Discount discount, Seat seat) {
        this.date_reserved = date_reserved;
        this.customer = customer;
        this.projection = projection;
        this.discount = discount;
        this.seat = seat;
    }

    public Ticket() {

    }

    public void setPrice(long price) {
        this.price = price;
    }
}
