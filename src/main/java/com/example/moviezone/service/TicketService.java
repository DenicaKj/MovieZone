package com.example.moviezone.service;

import com.example.moviezone.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> findAllTickets();
    List<Ticket> findAllByCustomer(Customer customer);
    Ticket saveWithDiscount(LocalDate date, Customer customer, Projection projection, Discount discount, Seat seat);
    Ticket saveWithout(LocalDate date,Customer customer,Projection projection,Seat seat);
    Ticket save(long price,Customer customer);
    Integer priceForTicket(int id);
    void delete(int id);
    Optional<Ticket> getById(int id);
}
