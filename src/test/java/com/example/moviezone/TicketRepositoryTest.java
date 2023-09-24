package com.example.moviezone;

import com.example.moviezone.model.*;
import com.example.moviezone.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase
public class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void testSaveTicket() {
        // Create a Ticket
        Ticket ticket = new Ticket(1000L, LocalDate.now(), new Customer(), new Projection(), new Discount(), new Seat());

        // Save the Ticket
        Ticket savedTicket = ticketRepository.save(ticket);

        // Verify that the Ticket was saved with an ID
        assertNotNull(savedTicket.getId_ticket());
    }

    @Test
    public void testFindTicketById() {
        // Create a Ticket and save it
        Ticket ticket = new Ticket(1000L, LocalDate.now(), new Customer(), new Projection(), new Discount(), new Seat());
        ticket = ticketRepository.save(ticket);

        // Find the Ticket by ID
        Ticket foundTicket = ticketRepository.findById(ticket.getId_ticket()).orElse(null);

        // Verify that the Ticket was found
        assertNotNull(foundTicket);
        assertEquals(ticket.getId_ticket(), foundTicket.getId_ticket());
    }

    @Test
    public void testUpdateTicket() {
        Ticket ticket = new Ticket(1000L, LocalDate.now(), new Customer(), new Projection(), new Discount(), new Seat());
        ticket = ticketRepository.save(ticket);

        ticket.setPrice(1200L);
        ticket = ticketRepository.save(ticket);

        Ticket updatedTicket = ticketRepository.findById(ticket.getId_ticket()).orElse(null);

        assertNotNull(updatedTicket);
        assertEquals(1200L, updatedTicket.getPrice());
    }

    @Test
    public void testDeleteTicket() {
        // Create a Ticket and save it
        Ticket ticket = new Ticket(1000L, LocalDate.now(), new Customer(), new Projection(), new Discount(), new Seat());
        ticket = ticketRepository.save(ticket);

        // Delete the Ticket
        ticketRepository.delete(ticket);

        // Try to find the deleted Ticket by ID
        Ticket deletedTicket = ticketRepository.findById(ticket.getId_ticket()).orElse(null);

        // Verify that the Ticket was deleted
        assertNull(deletedTicket);
    }
}