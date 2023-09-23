package com.example.moviezone;

import com.example.moviezone.model.*;
import com.example.moviezone.repository.TicketRepository;
import com.example.moviezone.service.TicketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc()
public class TicketServiceTest {

    @MockBean
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;


    @Test
    public void testFindAllTickets() {
        // Define some mock data for the repository
        List<Ticket> mockTickets = new ArrayList<>();
        // Populate mockTickets with test data as needed

        // Configure the mock behavior for the repository
        when(ticketRepository.findAll()).thenReturn(mockTickets);

        // Call the service method
        List<Ticket> result = ticketService.findAllTickets();

        // Use assertions to verify the result
        assertNotNull(result);
        assertEquals(mockTickets.size(), result.size());
        // Additional assertions as needed
    }

    @Test
    public void testFindAllByCustomer() {
        // Define some mock data for the repository
        Customer customer = new Customer(); // Create a Customer object as needed
        List<Ticket> mockTickets = new ArrayList<>();
        // Populate mockTickets with test data as needed

        // Configure the mock behavior for the repository
        when(ticketRepository.findAllByCustomer(customer)).thenReturn(mockTickets);

        // Call the service method
        List<Ticket> result = ticketService.findAllByCustomer(customer);

        // Use assertions to verify the result
        assertNotNull(result);
        assertEquals(mockTickets.size(), result.size());
        // Additional assertions as needed
    }

    @Test
    public void testSaveWithDiscount() {
        // Define input data for the method
        LocalDate date = LocalDate.now(); // Provide a valid date
        Customer customer = new Customer(); // Create a Customer object as needed
        Projection projection = new Projection(); // Create a Projection object as needed
        Discount discount = new Discount(); // Create a Discount object as needed
        Seat seat = new Seat(); // Create a Seat object as needed

        // Create a mock Ticket instance for the repository's save method
        Ticket mockTicket = new Ticket(date, customer, projection, discount, seat);

        // Configure the mock behavior for the repository
        when(ticketRepository.save(any(Ticket.class))).thenReturn(mockTicket);

        // Call the service method
        Ticket result = ticketService.saveWithDiscount(date, customer, projection, discount, seat);

        // Use assertions to verify the result
        assertNotNull(result);
        // Additional assertions as needed
    }

    @Test
    public void testSaveWithout() {
        // Define input data for the method
        LocalDate date = LocalDate.now(); // Provide a valid date
        Customer customer = new Customer(); // Create a Customer object as needed
        Projection projection = new Projection(); // Create a Projection object as needed
        Seat seat = new Seat(); // Create a Seat object as needed

        // Create a mock Ticket instance for the repository's save method
        Ticket mockTicket = new Ticket(date, customer, projection, seat);

        // Configure the mock behavior for the repository
        when(ticketRepository.save(any(Ticket.class))).thenReturn(mockTicket);

        // Call the service method
        Ticket result = ticketService.saveWithout(date, customer, projection, seat);

        // Use assertions to verify the result
        assertNotNull(result);
        // Additional assertions as needed
    }

    @Test
    public void testSave() {
        // Define input data for the method
        long price = 1000L; // Provide a valid price
        Customer customer = new Customer(); // Create a Customer object as needed

        // Create a mock Ticket instance for the repository's save method
        Ticket mockTicket = new Ticket(price, customer);

        // Configure the mock behavior for the repository
        when(ticketRepository.save(any(Ticket.class))).thenReturn(mockTicket);


        // Call the service method
        Ticket result = ticketService.save(price, customer);

        // Use assertions to verify the result
        assertNotNull(result);
        // Additional assertions as needed
    }

    @Test
    public void testPriceForTicket() {
        // Define input data for the method
        int ticketId = 1; // Provide a valid ticket ID
        Integer mockPrice = 100; // Provide a valid mock price

        // Configure the mock behavior for the repository
        when(ticketRepository.getPriceForTicket(ticketId)).thenReturn(mockPrice);

        // Call the service method
        Integer result = ticketService.priceForTicket(ticketId);

        // Use assertions to verify the result
        assertNotNull(result);
        assertEquals(mockPrice, result);
        // Additional assertions as needed
    }

    @Test
    public void testDelete() {
        // Define input data for the method
        int ticketId = 1; // Provide a valid ticket ID

        // Call the service method
        ticketService.delete(ticketId);

        // Verify that the repository's deleteById method was called with the correct argument
        verify(ticketRepository, times(1)).deleteById(ticketId);
    }
}
