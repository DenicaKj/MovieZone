package com.example.moviezone;

import com.example.moviezone.model.*;
import com.example.moviezone.model.procedures.TicketsCancelClass;
import com.example.moviezone.web.HomeController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc()
public class InputSpacePartitioning {

    public List<TicketsCancelClass> ticketsCancelClasses(List<Ticket> tickets){
        List<TicketsCancelClass> ticketsCancelClass = new ArrayList<>();
        LocalDateTime oneDayLater = LocalDateTime.now().plus(1, ChronoUnit.DAYS);
        if (tickets==null){
            return null;
        }
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getProjection() != null && tickets.get(i).getProjection().getDate_time_start() != null) {
                if (tickets.get(i).getProjection().getDate_time_start().isAfter(oneDayLater)) {
                    ticketsCancelClass.add(new TicketsCancelClass(tickets.get(i), true));
                } else {
                    ticketsCancelClass.add(new TicketsCancelClass(tickets.get(i), false));
                }
            } else {
                continue;
            }
        }
        return ticketsCancelClass;
    }

    @Test
    public void testTicketsCancelClassesWithEmptyList() {
        List<Ticket> emptyList = new ArrayList<>();
        List<TicketsCancelClass> result = ticketsCancelClasses(emptyList);
        assertTrue(result.isEmpty(), "Result should be an empty list for an empty input.");
    }

    @Test
    public void testTicketsCancelClassesWithNullList() {
        List<TicketsCancelClass> result = ticketsCancelClasses(null);
        assertNull(result, "Result should be null for a null input.");
    }
    @Test
    public void testAllTicketsCantCancel() {
        List<Ticket> tickets = new ArrayList<>();

        Projection projection1 = new Projection(LocalDateTime.now().minusDays(1),"3d",LocalDateTime.now().minusDays(1),new Film(),new Discount());
        Projection projection2 = new Projection(LocalDateTime.now(),"3d",LocalDateTime.now(),new Film(),new Discount());
        Projection projection3 = new Projection(LocalDateTime.now().minusDays(3),"3d",LocalDateTime.now().minusDays(3),new Film(),new Discount());

        Ticket ticket1= new Ticket(LocalDate.now(),new Customer(),projection1,new Seat());
        Ticket ticket2= new Ticket(LocalDate.now(),new Customer(),projection2,new Seat());
        Ticket ticket3= new Ticket(LocalDate.now(),new Customer(),projection3,new Seat());
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);

        List<TicketsCancelClass> result = ticketsCancelClasses(tickets);

        for (int i = 0; i < result.size(); i++) {
            assertFalse(result.get(i).canCancel);
        }
    }

    @Test
    public void testAllTicketsCanCancel() {
        List<Ticket> tickets = new ArrayList<>();

        Projection projection1 = new Projection(LocalDateTime.now().plusDays(3),"3d",LocalDateTime.now().plusDays(3),new Film(),new Discount());
        Projection projection2 = new Projection(LocalDateTime.now().plusDays(4),"3d",LocalDateTime.now().plusDays(4),new Film(),new Discount());
        Projection projection3 = new Projection(LocalDateTime.now().plusDays(10),"3d",LocalDateTime.now().plusDays(10),new Film(),new Discount());

        Ticket ticket1= new Ticket(LocalDate.now(),new Customer(),projection1,new Seat());
        Ticket ticket2= new Ticket(LocalDate.now(),new Customer(),projection2,new Seat());
        Ticket ticket3= new Ticket(LocalDate.now(),new Customer(),projection3,new Seat());
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);

        List<TicketsCancelClass> result = ticketsCancelClasses(tickets);

        for (int i = 0; i < result.size(); i++) {
            assertTrue(result.get(i).canCancel);
        }
    }

    @Test
    public void testTicketsCanAndCantCancel() {
        List<Ticket> tickets = new ArrayList<>();

        Projection projection1 = new Projection(LocalDateTime.now().plusDays(3),"3d",LocalDateTime.now().plusDays(3),new Film(),new Discount());
        Projection projection2 = new Projection(LocalDateTime.now().plusDays(4),"3d",LocalDateTime.now().plusDays(4),new Film(),new Discount());
        Projection projection3 = new Projection(LocalDateTime.now().plusDays(10),"3d",LocalDateTime.now().plusDays(10),new Film(),new Discount());

        Ticket ticket1= new Ticket(LocalDate.now(),new Customer(),projection1,new Seat());
        Ticket ticket2= new Ticket(LocalDate.now(),new Customer(),projection2,new Seat());
        Ticket ticket3= new Ticket(LocalDate.now(),new Customer(),projection3,new Seat());
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);

        Projection projection4 = new Projection(LocalDateTime.now().minusDays(1),"3d",LocalDateTime.now().minusDays(1),new Film(),new Discount());
        Projection projection5 = new Projection(LocalDateTime.now(),"3d",LocalDateTime.now(),new Film(),new Discount());
        Projection projection6 = new Projection(LocalDateTime.now().minusDays(3),"3d",LocalDateTime.now().minusDays(3),new Film(),new Discount());

        Ticket ticket4= new Ticket(LocalDate.now(),new Customer(),projection4,new Seat());
        Ticket ticket5= new Ticket(LocalDate.now(),new Customer(),projection5,new Seat());
        Ticket ticket6= new Ticket(LocalDate.now(),new Customer(),projection6,new Seat());
        tickets.add(ticket4);
        tickets.add(ticket5);
        tickets.add(ticket6);

        List<TicketsCancelClass> result = ticketsCancelClasses(tickets);

        int numbCanCancel=0;
        int numbCantCancel= 0;

        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).canCancel)
                numbCanCancel+=1;
            else
                numbCantCancel+=1;
        }
        assertEquals(3,numbCanCancel);
        assertEquals(3,numbCantCancel);
    }
}
