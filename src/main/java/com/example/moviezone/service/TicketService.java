package com.example.moviezone.service;

import com.example.moviezone.model.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> findAllTickets();
    List<Ticket> findAllByCustomer();
}
