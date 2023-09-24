package com.example.moviezone;

import com.example.moviezone.model.*;
import com.example.moviezone.model.procedures.TicketsCancelClass;
import com.example.moviezone.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest(classes = MovieZoneApplication.class)
public class ThymeleafTemplateTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FilmService filmService; // Assuming you have a FilmService
    @MockBean
    private EventService eventService; // Assuming you have an EventService
    @MockBean
    private TicketService ticketService;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private SeatService seatService;
    @MockBean
    private UserService userService;
    @MockBean
    private CustomerRatesFilmService customerRatesFilmService;

    @Test
    public void testTemplateHomeRendering() throws Exception {
        // Create sample film and event objects
        List<Film> films = new ArrayList<>();
        Film film = new Film();
        film.setName("Sample Film");
        film.setDuration(120);
        film.setGenre("Action");
        film.setUrl("/sample-film.jpg");
        film.setId_film(1);

        List<Event> events = new ArrayList<>();
        Event event = new Event();
        event.setTheme("Sample Event");
        event.setStart_date(LocalDate.now());
        event.setDuration("3 hours");
        event.setImg_url("/sample-event.jpg");
        event.setId_event(1);

        films.add(film);
        events.add(event);

        // Define behavior of the mock services
        when(filmService.findAllFilms()).thenReturn(films);
        when(eventService.findAllEvents()).thenReturn(events);

        // Perform a GET request to a URL that uses the template
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expect HTTP 200 OK
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Нови Филмови")))
                .andExpect(MockMvcResultMatchers.model().attribute("films", films))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Следни Настани:")))
                .andExpect(MockMvcResultMatchers.model().attribute("events", events))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Sample Film")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Sample Event")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Action")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("120")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("3")));
    }
    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    public void testTemplateMyTicketsRendering() throws Exception {
        // Create sample customer and ticket objects
        Customer customer = new Customer();
        customer.setUsername("testuser");

        Ticket ticket = new Ticket();
        ticket.setId_ticket(1);
        ticket.setPrice(100);
        ticket.setDate_reserved(LocalDate.now());

        Seat seat = new Seat();
        seat.setSeat_number(1);
        ticket.setSeat(seat);

        Projection projection = new Projection();
        projection.setFilm(new Film());
        projection.setDate_time_start(LocalDateTime.now().plusDays(2)); // Future date

        ticket.setProjection(projection);

        List<TicketsCancelClass> ticketsCancelClass = new ArrayList<>();
        ticketsCancelClass.add(new TicketsCancelClass(ticket, true)); // Can cancel

        // Define behavior of the mock services
        when(customerService.findByUsername("testuser")).thenReturn(customer);
        when(ticketService.findAllByCustomer(customer)).thenReturn(Collections.singletonList(ticket));

        // Perform a GET request to the URL that uses the template
        mockMvc.perform(MockMvcRequestBuilders.get("/myTickets"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expect HTTP 200 OK
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Филм:")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Откажи")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("100")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Број на седиште:")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("1")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("2"))); // Check for the values that should be displayed
    }

    @Test
    public void testTemplateFilmDetailsRendering() throws Exception {
        // Create sample film and genres
        Film film = new Film();
        film.setId_film(1);
        film.setName("Sample Film");
        film.setDuration(120);
        film.setGenre("Action");
        film.setAge_category("PG-13");
        film.setDirector("Sample Director");

        // Define behavior of the mock services
        when(filmService.getFilmById(1L)).thenReturn(Optional.of(film));
        when(customerRatesFilmService.avg_rating(1)).thenReturn(4.5);

        // Perform a GET request to the URL that uses the template
        mockMvc.perform(MockMvcRequestBuilders.get("/getFilm/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expect HTTP 200 OK
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Sample Film")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Action")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("PG-13")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("120")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("Sample Director")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("4.5")));
    }
}