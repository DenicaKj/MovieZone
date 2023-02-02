package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Ticket;
import com.example.moviezone.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public List<Ticket> findAllTickets() {
        return null;
    }

    @Override
    public List<Ticket> findAllByCustomer() {
        return null;
    }
}
