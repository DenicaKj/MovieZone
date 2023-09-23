package com.example.moviezone;

import com.example.moviezone.MovieZoneApplication;
import com.example.moviezone.model.Customer;
import com.example.moviezone.model.Discount;
import com.example.moviezone.model.Projection;
import com.example.moviezone.model.Seat;
import com.example.moviezone.model.Ticket;
import com.example.moviezone.repository.TicketRepository;
import com.example.moviezone.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = MovieZoneApplication.class)
@Transactional
@AutoConfigureTestEntityManager
@SpringBootTest(classes = MovieZoneApplication.class)
public class TicketServiceIntegrationTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EntityManager entityManager;


    @Test
    public void testSaveWithDiscount() {
        long price = 1000L;
        Customer customer = new Customer();
        customer.setId_user(4);
        Seat seat = new Seat();
        seat.setId_seat(1);
        Projection projection= new Projection();
        projection.setId_projection(1);
        Discount discount= new Discount();
        discount.setId_discount(1);

        // Call the service method
        Ticket savedTicket = ticketService.saveWithDiscount(LocalDate.now(), customer,projection,discount,seat);

        // Perform assertions to validate the result
        assertNotNull(savedTicket);
        // Add more assertions as needed
    }

    @Test
    public void testSaveWithout() {
        // Define test data
        long price = 1000L;
        Customer customer = new Customer();
        customer.setId_user(4);
        Seat seat = new Seat();
        seat.setId_seat(1);
        Projection projection= new Projection();
        projection.setId_projection(1);

        // Call the service method
        Ticket savedTicket = ticketService.saveWithout(LocalDate.now(), customer,projection,seat);

        // Perform assertions to validate the result
        assertNotNull(savedTicket);
        // Add more assertions as needed
    }


    @Test
    public void testPriceForTicket() {
        // Define test data and save a ticket to the database
        long price = 1000L;
        Customer customer = new Customer();
        customer.setId_user(4);
        Seat seat = new Seat();
        seat.setId_seat(1);
        Projection projection= new Projection();
        projection.setId_projection(1);
        Discount discount= new Discount();
        discount.setId_discount(1);
        Ticket ticket = new Ticket(price,LocalDate.now(), customer,projection,discount,seat);
        entityManager.persist(ticket);
        entityManager.flush();

        // Call the service method
        Integer fetchedPrice = ticketService.priceForTicket(ticket.getId_ticket());

        // Perform assertions to validate the result
        assertNotNull(fetchedPrice);
        assertEquals( price, fetchedPrice.longValue());
        // Add more assertions as needed
    }

    @Test
    public void testDelete() {
        // Define test data and save a ticket to the database
        long price = 1000L;
        Customer customer = new Customer();
        customer.setId_user(4);
        Seat seat = new Seat();
        seat.setId_seat(1);
        Projection projection= new Projection();
        projection.setId_projection(1);
        Discount discount= new Discount();
        discount.setId_discount(1);
        Ticket ticket = new Ticket(price,LocalDate.now(), customer,projection,discount,seat);
        entityManager.persist(ticket);
        entityManager.flush();

        // Call the service method to delete the ticket
        ticketService.delete(ticket.getId_ticket());

        // Attempt to retrieve the ticket after deletion
        Ticket deletedTicket = entityManager.find(Ticket.class, ticket.getId_ticket());

        // Perform assertions to validate the result
        assertNull(deletedTicket);
    }

    // Add more tests as needed
}