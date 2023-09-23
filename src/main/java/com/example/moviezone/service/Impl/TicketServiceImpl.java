package com.example.moviezone.service.Impl;

import com.example.moviezone.model.*;
import com.example.moviezone.repository.TicketRepository;
import com.example.moviezone.service.TicketService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Ticket saveWithDiscount(LocalDate date, Customer customer, Projection projection, Discount discount, Seat seat) {
        Ticket t=new Ticket(date,customer,projection,discount,seat);
        return ticketRepository.save(t);
    }

    @Override
    public Ticket saveWithout(LocalDate date, Customer customer, Projection projection, Seat seat) {
        Ticket t=new Ticket(date,customer,projection,seat);
        return ticketRepository.save(t);
    }

    @Override
    public Ticket save(long price, Customer customer) {
        return ticketRepository.save(new Ticket(price,customer));
    }

    @Override
    public Integer priceForTicket(int id) {
        return Math.toIntExact(ticketRepository.findById(id).get().getPrice());
    }

    @Override
    public void delete(int id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public Optional<Ticket> getById(int id) {
        return ticketRepository.findById(id);
    }


}
