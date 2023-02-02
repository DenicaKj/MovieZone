package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Customer;
import com.example.moviezone.model.Ticket;
import com.example.moviezone.repository.TicketRepository;
import com.example.moviezone.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findAllByCustomer(Customer customer) {
        return ticketRepository.findAllByCustomer(customer);
    }
}
