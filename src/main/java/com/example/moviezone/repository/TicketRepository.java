package com.example.moviezone.repository;

import com.example.moviezone.model.Customer;
import com.example.moviezone.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    List<Ticket> findAllByCustomer(Customer customer);
    @Procedure("project.getPriceForTicket")
    Integer getPriceForTicket(int id);
}
