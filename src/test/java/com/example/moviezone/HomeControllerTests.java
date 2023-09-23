package com.example.moviezone;

import com.example.moviezone.model.*;
import com.example.moviezone.model.exceptions.UserNotFoundException;
import com.example.moviezone.model.manytomany.CustomerRatesFilm;
import com.example.moviezone.repository.UserRepository;
import com.example.moviezone.service.*;
import com.example.moviezone.web.HomeController;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc()
public class HomeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;
    @MockBean
    private EventService eventService;
    @MockBean
    private ProjectionService projectionService;
    @MockBean
    private WorkerService workerService;
    @MockBean
    private CinemaService cinemaService;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private SeatService seatService;
    @MockBean
    private Projection_RoomService projectionRoomService;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private TicketService ticketService;
    @MockBean
    private DiscountService discountService;
    @MockBean
    private CinemaOrganizesEventService cinemaOrganizesEventService;
    @MockBean
    private CustomerRatesFilmService customerRatesFilmService;
    @MockBean
    private CinemaPlaysFilmService cinemaPlaysFilmService;
    @MockBean
    private CustomerIsInterestedInEventService customerIsInterestedInEventService;

    @MockBean
    private UserService userService;

    // Other @MockBean declarations for your controller's dependencies.

    @Test
    public void testGetHomePage() throws Exception {
        List<Film> films = Arrays.asList(new Film(), new Film()); // Add sample films
        List<Event> events = Arrays.asList(new Event(), new Event()); // Add sample events

        given(filmService.findAllFilms()).willReturn(films);
        given(eventService.findAllEvents()).willReturn(events);

        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attribute("bodyContent", "home"))
                .andExpect(model().attributeExists("films"))
                .andExpect(model().attributeExists("events"))
                .andExpect(model().attribute("films", equalTo(films))) // Check if films in the model match the mock data
                .andExpect(model().attribute("events", equalTo(events)));
    }

    @Test
    public void testGetFilm() throws Exception {
        // Mock data for a film
        Film film = new Film();
        film.setId_film(1); // Set the ID of the film
        film.setGenre("Action");
        given(filmService.getFilmById(1L)).willReturn(Optional.of(film));

        mockMvc.perform(get("/home/getFilm/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attribute("bodyContent", "film"))
                .andExpect(model().attributeExists("film"))
                .andExpect(model().attribute("film", equalTo(film)));
    }

    @Test
    public void testGetEvent() throws Exception {
        // Mock data for an event
        Event event = new Event();
        event.setId_event(1); // Set the ID of the event

        given(eventService.getEventById(1L)).willReturn(Optional.of(event));

        mockMvc.perform(get("/home/getEvent/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attribute("bodyContent", "event"))
                .andExpect(model().attributeExists("event"))
                .andExpect(model().attribute("event", equalTo(event)));
    }

    @Test
    public void testGetProjectionsFromFilm() throws Exception {
        // Mock data for a film
        Film film = new Film();
        film.setId_film(1); // Set the ID of the film

        // Mock data for projections related to the film
        List<Projection> projections = new ArrayList<>();
        Projection projection1 = new Projection();
        projection1.setId_projection(1);
        projections.add(projection1);

        given(filmService.getFilmById(1L)).willReturn(Optional.of(film));
        given(projectionService.getProjectionsForFilms(1)).willReturn(projections);
        given(categoryService.findAllCategories()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/home/getProjections/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attribute("bodyContent", "projectionsForFilm"))
                .andExpect(model().attributeExists("film"))
                .andExpect(model().attribute("film", equalTo(film)))
                .andExpect(model().attributeExists("projections"))
                .andExpect(model().attribute("projections", equalTo(projections)))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    @WithMockUser
    public void testGetSeats() throws Exception {
        // Mock data for a category
        Category category = new Category();
        category.setIdcategory(1); // Set the ID of the category

        Film film = new Film();
        film.setId_film(1);

        // Mock data for a projection
        Projection projection = new Projection();
        projection.setId_projection(1);

        Projection_Room projection_room = new Projection_Room();
        projection_room.setId_room(1);
        // Mock data for seats
        List<Seat> seats = new ArrayList<>();
        Seat seat1 = new Seat();
        seat1.setId_seat(1);
        seats.add(seat1);

        given(categoryService.getCategoryById(1)).willReturn(Optional.of(category));
        given(projectionService.findById(1)).willReturn(projection);
        given(projectionRoomService.getRoomByProjection(projection.getId_projection())).willReturn(new ArrayList<>());
        given(seatService.findAllByRoomAndCategory(projection, null, category)).willReturn(seats);
        given(filmService.getFilmById(1L)).willReturn(Optional.of(film));

        mockMvc.perform(get("/home/getSeats/{id}",1L)
                        .param("id","1")
                        .param("id_category", "1")
                        .param("film", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attribute("bodyContent", "seats"))
                .andExpect(model().attributeExists("film"))
                .andExpect(model().attributeExists("projection"))
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attributeExists("seats"))
                .andExpect(model().attribute("seats", equalTo(seats)));
    }

    @Test
    public void testGetLoginPage() throws Exception {
        // Perform a GET request to the "/login" endpoint and expect the "login" view
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(view().name("master-template")) // Expect the view name "master-template"
                .andExpect(model().attribute("bodyContent", "login")); // Expect the "bodyContent" attribute to be set to "login"
    }

    @Test
    public void testGetRegisterPage() throws Exception {
        // Perform a GET request to the "/register" endpoint and expect the "register" view
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(view().name("master-template")) // Expect the view name "master-template"
                .andExpect(model().attribute("bodyContent", "register")); // Expect the "bodyContent" attribute to be set to "register"
    }

    @Test
    public void testLogin() throws Exception {
        // Mock user data
        User mockUser = new User();
        mockUser.setFirst_name("John");
        mockUser.setUsername("john.doe");
        mockUser.setPassword("password"); // Set the password for the mock user

        // Configure UserDetailsService to return the mock user
        UserDetails userDetails = mockUser;

        given(userService.login(anyString(), anyString())).willReturn(mockUser);
        given(userService.findByUsername(anyString())).willReturn((User) userDetails); // Mock UserDetailsService

        mockMvc.perform(post("/login")
                        .param("username", "john.doe")
                        .param("password", "password")
                        .with(csrf()) // Include CSRF token
                        .with(user("john.doe").password("password").roles("USER")) // Simulate authenticated user
                )
                .andExpect(status().is3xxRedirection()) // Redirects to /home on successful login
                .andExpect(redirectedUrl("/home"))
                .andExpect(authenticated().withUsername("john.doe")); // Check if the user is authenticated
    }

    @Test
    public void testLoginFailure() throws Exception {
        // Mock a user login failure
        String invalidUsername = "invalidUser";
        String invalidPassword = "invalidPassword";

        given(userService.login(invalidUsername, invalidPassword)).willThrow(new UserNotFoundException());

        mockMvc.perform(post("/login")
                        .param("username", invalidUsername)
                        .param("password", invalidPassword)
                        .with(csrf())) // Include CSRF token
                .andExpect(status().isOk()) // Stay on the login page on failure
                .andExpect(view().name("login"))
                .andExpect(model().attribute("hasError", true));
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(post("/register")
                        .param("username", "newUser")
                        .param("first_name", "John")
                        .param("last_name", "Doe")
                        .param("password", "password")
                        .param("repeatedPassword", "password")
                        .param("email", "john@example.com")
                        .param("number", "1234567890")
                        .param("role", "ROLE_USER")
                        .with(csrf())) // Include CSRF token
                .andExpect(status().is3xxRedirection()) // Redirects to /login or /registerWorker based on role
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void testRegisterAsAdmin() throws Exception {
        mockMvc.perform(post("/register")
                        .param("username", "adminUser")
                        .param("first_name", "Admin")
                        .param("last_name", "User")
                        .param("password", "adminPassword")
                        .param("repeatedPassword", "adminPassword")
                        .param("email", "admin@example.com")
                        .param("number", "9876543210")
                        .param("role", "ROLE_ADMIN")
                        .with(csrf())) // Include CSRF token
                .andExpect(status().is3xxRedirection()) // Redirects to /registerWorker
                .andExpect(redirectedUrl("/registerWorker"));
    }

    @Test
    public void testGetRegisterWorkerPage() throws Exception {
        mockMvc.perform(get("/registerWorker"))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attributeExists("cinemas"))
                .andExpect(model().attribute("bodyContent", "registerWorker"));
    }

    @Test
    public void testHandleWorkerRegister() throws Exception {
        // Mock data and dependencies
        Cinema mockedCinema = new Cinema();
        mockedCinema.setId_cinema(1);
        when(cinemaService.findCinemaById(1)).thenReturn(mockedCinema);
        doNothing().when(userService).registerWorker(any(), any(), any(), any(), any(), any(), any(), any(), any(), any());

        // Create a mock HttpSession with required attributes
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("username", "mockUsername");
        session.setAttribute("first_name", "mockFirstName");
        session.setAttribute("last_name","mockLastName");
        session.setAttribute("email","mockEmail");
        session.setAttribute("password","mockPassword");
        session.setAttribute("number","mockNumber");
        // Add other session attributes as needed

        // Perform a POST request to /finishRegister
        mockMvc.perform(post("/finishRegister")
                        .session(session)
                        .param("position", "mockPosition")
                        .param("work_hours_from", "mockFrom")
                        .param("work_hours_to", "mockTo")
                        .param("id_cinema", "1")
                        .with(csrf())) // Include CSRF token
                .andExpect(status().is3xxRedirection()) // Redirects to /login
                .andExpect(redirectedUrl("/login"));

        // Verify that userService.registerWorker is called with expected arguments
        verify(userService).registerWorker("mockFirstName", "mockLastName", "mockUsername", "mockEmail", "mockNumber", "mockPassword", "mockPosition", "mockFrom", "mockTo", mockedCinema);
    }

    @Test
    public void testGetProjectionsPage() throws Exception {
        // Mock data for films
        List<Film> films = Arrays.asList(new Film(), new Film());
        when(filmService.getFilmsNow()).thenReturn(films);

        mockMvc.perform(get("/projections"))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attributeExists("cinemas"))
                .andExpect(model().attribute("films", equalTo(films)))
                .andExpect(model().attribute("bodyContent", "projections"));
    }

    @Test
    public void testGetEventsPage() throws Exception {
        // Mock data for events
        List<Event> events = Arrays.asList(new Event(), new Event());
        when(eventService.getEventsNow()).thenReturn(events);

        mockMvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attributeExists("cinemas"))
                .andExpect(model().attribute("events", equalTo(events)))
                .andExpect(model().attribute("bodyContent", "events"));
    }

    @Test
    @WithMockUser
    public void testGetMyTicketsPage() throws Exception {
        // Mock data for customer and tickets
        Customer mockedCustomer = new Customer();
        List<Ticket> tickets = Arrays.asList(new Ticket(), new Ticket());
        when(customerService.findByUsername(any())).thenReturn(mockedCustomer);
        when(ticketService.findAllByCustomer(mockedCustomer)).thenReturn(tickets);

        mockMvc.perform(get("/myTickets"))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attributeExists("tickets"))
                .andExpect(model().attribute("bodyContent", "myTickets"));
    }

    @Test
    @WithMockUser
    public void testCancelTicket() throws Exception {
        // Mock ticket ID to delete
        int ticketIdToDelete = 1;
        doNothing().when(ticketService).delete(ticketIdToDelete);

        mockMvc.perform(post("/cancelTicket/{id}", ticketIdToDelete))
                .andExpect(status().is3xxRedirection()) // Redirects to /myTickets
                .andExpect(redirectedUrl("/myTickets"));

        // Verify that ticketService.delete is called with the expected ticket ID
        verify(ticketService).delete(ticketIdToDelete);
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "ADMIN")
    public void testGetAddProjectionPage() throws Exception {
        mockMvc.perform(get("/addProjection"))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attributeExists("films"))
                .andExpect(model().attributeExists("projection_rooms"))
                .andExpect(model().attributeExists("discounts"))
                .andExpect(model().attribute("bodyContent", "addProjection"));
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "ADMIN")
    public void testGetAddDiscountPage() throws Exception {
        mockMvc.perform(get("/addDiscount"))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attribute("bodyContent", "addDiscount"));

    }

    @Test
    @WithMockUser(username = "mockUser", roles = "ADMIN")
    public void testGetAddEventPage() throws Exception {
        mockMvc.perform(get("/addEvent"))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attribute("bodyContent", "addEvent"));
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "ADMIN")
    public void testGetAddFilmPage() throws Exception {
        mockMvc.perform(get("/addFilm"))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attribute("bodyContent", "addFilm"));
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "ADMIN")
    public void testSaveEvent() throws Exception {
        mockMvc.perform(post("/addD")
                        .param("validity", "2023-12-31")
                        .param("type", "mockType")
                        .param("code", "mockCode")
                        .param("percent", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));

        // Verify that the service method is called with the expected parameters
        verify(discountService).save("mockCode", "mockType", LocalDate.of(2023, 12, 31), 10);
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "ADMIN")
    public void testSaveProjection() throws Exception {
        mockMvc.perform(post("/addP")
                        .param("date_time_start", "2023-12-31T12:00")
                        .param("date_time_end", "2023-12-31T14:00")
                        .param("type_of_technology", "mockTechnology")
                        .param("id_film", "1")
                        .param("id_room", "2")
                        .param("id_discount", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));

        // Verify that the service method is called with the expected parameters
        verify(projectionService).save(
                LocalDateTime.of(2023, 12, 31, 12, 0),
                LocalDateTime.of(2023, 12, 31, 14, 0),
                "mockTechnology",
                1,
                2,
                3
        );
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "ADMIN")
    public void testAddEvent() throws Exception {
        mockMvc.perform(post("/addE")
                        .param("start_date", "2023-09-25")
                        .param("theme", "Test Theme")
                        .param("duration", "2 hours")
                        .param("img_url", "test.jpg")
                        .param("repeating", "Every Saturday")
                )
                .andExpect(status().is3xxRedirection()) // Redirects to /home
                .andExpect(redirectedUrl("/home"));

        verify(eventService).save(LocalDate.parse("2023-09-25"), "Test Theme", "2 hours", "Every Saturday", "test.jpg");
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "ADMIN")
    public void testAddFilm() throws Exception {
        mockMvc.perform(post("/addF")
                        .param("name", "Test Film")
                        .param("duration", "120")
                        .param("actors", "Actor 1, Actor 2")
                        .param("genre", "Action")
                        .param("age_category", "PG-13")
                        .param("url", "test.jpg")
                        .param("director", "Test Director")
                        .param("start_date", "2023-09-25")
                        .param("end_date", "2023-09-30")
                )
                .andExpect(status().is3xxRedirection()) // Redirects to /home
                .andExpect(redirectedUrl("/home"));

        verify(filmService).save("Test Film", 120, "Actor 1, Actor 2", "Action", "PG-13", "test.jpg", "Test Director", LocalDate.parse("2023-09-25"), LocalDate.parse("2023-09-30"));
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "ADMIN")
    public void testSaveCinemaOrganizesEvent() throws Exception {
        mockMvc.perform(post("/addCinemaOrganizesEvent")
                        .param("id_cinema", "1")
                        .param("id_event", "2")
                )
                .andExpect(status().is3xxRedirection()) // Redirects to /home
                .andExpect(redirectedUrl("/home"));

        verify(cinemaOrganizesEventService).save(1, 2);
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "ADMIN")
    public void testSaveCinemaPlaysFilm() throws Exception {
        mockMvc.perform(post("/addCinemaPlaysFilm")
                        .param("id_cinema", "1")
                        .param("id_film", "2")
                )
                .andExpect(status().is3xxRedirection()) // Redirects to /home
                .andExpect(redirectedUrl("/home"));

        verify(cinemaPlaysFilmService).save(1, 2);
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "USER")
    public void testCreateTicketForReservation() throws Exception {
        // Mock data for a ticket reservation
        Ticket t = new Ticket();
        t.setId_ticket(1);

        // Mock the category, seat, discount, and projection
        Category category = new Category();
        category.setExtra_amount(0);

        Seat seat = new Seat();
        seat.setCategory(category);

        Discount discount = new Discount();
        discount.setPercent(0);
        discount.setCode("TestDiscount");

        Projection projection = new Projection();
        projection.setDiscount(discount);

        // Mock the service methods
        when(customerService.findByUsername(any())).thenReturn(new Customer());
        when(projectionService.findById(anyInt())).thenReturn(projection);
        when(seatService.getSeatById(anyInt())).thenReturn(Optional.of(seat));
        when(ticketService.saveWithDiscount(any(), any(), any(), any(), any())).thenReturn(t);
        when(ticketService.saveWithout(any(), any(), any(), any())).thenReturn(t);
        when(ticketService.priceForTicket(anyInt())).thenReturn(1);

        mockMvc.perform(post("/home/makeReservation")
                        .param("film", "1")
                        .param("projection", "1")
                        .param("id_seat", "1")
                        .param("discount", "TestDiscount")
                )
                .andExpect(status().is3xxRedirection()) // Redirects to /myTickets
                .andExpect(redirectedUrl("/myTickets"));

        // Verify using argument matchers
        verify(ticketService).saveWithDiscount(
                eq(LocalDate.now()),
                any(Customer.class),
                eq(projection),
                eq(discount),
                eq(seat)
        );
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "USER")
    public void testAddRating() throws Exception {
        // Mock data for adding a rating
        Customer customer = new Customer();
        customer.setId_user(1);
        CustomerRatesFilm customerRatesFilm = new CustomerRatesFilm();
        when(customerService.findByUsername(anyString())).thenReturn(customer);
        when(customerRatesFilmService.addRating(anyInt(), anyInt(), anyDouble())).thenReturn(customerRatesFilm);

        mockMvc.perform(post("/addRating/{id}", 1)
                        .param("rate", "5")
                )
                .andExpect(status().is3xxRedirection()) // Redirects to /home/getFilm/{id}
                .andExpect(redirectedUrl("/home/getFilm/1"));

        verify(customerRatesFilmService).addRating(anyInt(), eq(1), eq(5.0d));
    }


    @Test
    @WithMockUser(username = "mockUser", roles = "WORKER")
    public void testGetWorkerProfile() throws Exception {
        // Mock data for a worker
        Cinema cinema = new Cinema();
        cinema.setName("test");
        Worker worker = new Worker();
        worker.setUsername("mockUser");
        worker.setCinema(cinema);
        when(workerService.getWorkerByUsername("mockUser")).thenReturn(worker);

        mockMvc.perform(get("/profileWorker"))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attributeExists("worker"))
                .andExpect(model().attribute("bodyContent", "profileWorker"))
                .andExpect(model().attribute("worker", worker));
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "USER")
    public void testGetUserProfile() throws Exception {
        // Mock data for a customer
        Customer customer = new Customer();
        customer.setUsername("mockUser");
        customer.setId_user(1);
        List<Event> events = Arrays.asList(new Event(), new Event()); // Mock events for the customer

        when(customerService.findByUsername("mockUser")).thenReturn(customer);
        when(eventService.getEventsForCustomer(customer.getId_user())).thenReturn(events);

        mockMvc.perform(get("/profileUser"))
                .andExpect(status().isOk())
                .andExpect(view().name("master-template"))
                .andExpect(model().attributeExists("customer"))
                .andExpect(model().attributeExists("events"))
                .andExpect(model().attribute("bodyContent", "profileUser"))
                .andExpect(model().attribute("customer", customer))
                .andExpect(model().attribute("events", events));
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "USER")
    public void testAddInterestedEvent() throws Exception {
        // Mock data
        Customer customer = new Customer();
        customer.setId_user(1);
        Long eventId = 1L;

        when(customerService.findByUsername("mockUser")).thenReturn(customer);

        mockMvc.perform(post("/home/addInterestedEvent/{id}", eventId)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profileUser"));

        verify(customerIsInterestedInEventService).add(customer.getId_user(), eventId.intValue());
    }

    @Test
    @WithMockUser(username = "mockUser", roles = "USER")
    public void testDeleteInterestedEvent() throws Exception {
        // Mock data
        Customer customer = new Customer();
        Event event = new Event();
        customer.setUsername("mockUser");
        event.setId_event(1);

        when(customerService.findByUsername("mockUser")).thenReturn(customer);
        when(eventService.getEventById(1L)).thenReturn(Optional.of(event));

        mockMvc.perform(post("/home/deleteInterestedEvent/{id}", 1L)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profileUser"));

        verify(customerIsInterestedInEventService).delete(customer, event);
    }
}
