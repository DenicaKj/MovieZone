package com.example.moviezone.repository;

import com.example.moviezone.model.Customer;
import com.example.moviezone.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    List<Ticket> findAllByCustomer(Customer customer);
}
