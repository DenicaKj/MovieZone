package com.example.moviezone.web;


import com.example.moviezone.model.*;
import com.example.moviezone.model.enums.GenreEnum;
import com.example.moviezone.model.exceptions.PasswordsDoNotMatchException;

import com.example.moviezone.model.exceptions.UserNotFoundException;
import com.example.moviezone.model.manytomany.ProjectionIsPlayedInRoom;

import com.example.moviezone.model.procedures.FilmsReturnTable;

import com.example.moviezone.model.procedures.TicketsCancelClass;
import com.example.moviezone.repository.CustomerCinemaReportRepository;
import com.example.moviezone.repository.FilmReportRepository;
import com.example.moviezone.repository.MonthlyCinemaReportRepository;
import com.example.moviezone.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"/","/home"})
public class HomeController {

private final FilmService filmService;
private final UserService userService;
private final ProjectionService projectionService;
private final EventService eventService;
private final TicketService ticketService;
private final WorkerService workerService;
private final CustomerRatesFilmService customerRatesFilmService;
private final CinemaService cinemaService;
private final CinemaOrganizesEventService cinemaOrganizesEventService;
private final CinemaPlaysFilmService cinemaPlaysFilmService;
private final ProjectionIsPlayedInRoomService projectionIsPlayedInRoomService;
private final CategoryService categoryService;
private final SeatService seatService;
private final CustomerService customerService;
private final Projection_RoomService projectionRoomService;
private final CustomerIsInterestedInEventService customerIsInterestedInEventService;
private final DiscountService discountService;
private final MonthlyCinemaReportRepository monthlyCinemaReportRepository;
private final FilmReportRepository filmReportRepository;
private final CustomerCinemaReportRepository customerCinemaReportRepository;

    public HomeController(FilmService filmService, UserService userService, ProjectionService projectionService, EventService eventService, TicketService ticketService, WorkerService workerService, CustomerRatesFilmService customerRatesFilmService, CinemaService cinemaService, CinemaOrganizesEventService cinemaOrganizesEventService, CinemaPlaysFilmService cinemaPlaysFilmService, ProjectionIsPlayedInRoomService projectionIsPlayedInRoomService, CategoryService categoryService, SeatService seatService, CustomerService customerService, Projection_RoomService projectionRoomService, CustomerIsInterestedInEventService customerIsInterestedInEventService, DiscountService discountService, MonthlyCinemaReportRepository monthlyCinemaReportRepository, FilmReportRepository filmReportRepository, CustomerCinemaReportRepository customerCinemaReportRepository)
    {

        this.filmService = filmService;
        this.userService = userService;
        this.projectionService = projectionService;
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.workerService = workerService;
        this.customerRatesFilmService = customerRatesFilmService;
        this.cinemaService = cinemaService;
        this.cinemaOrganizesEventService = cinemaOrganizesEventService;
        this.cinemaPlaysFilmService = cinemaPlaysFilmService;
        this.projectionIsPlayedInRoomService = projectionIsPlayedInRoomService;
        this.categoryService = categoryService;
        this.seatService = seatService;
        this.customerService = customerService;
        this.projectionRoomService = projectionRoomService;
        this.customerIsInterestedInEventService = customerIsInterestedInEventService;
        this.discountService = discountService;
        this.monthlyCinemaReportRepository = monthlyCinemaReportRepository;
        this.filmReportRepository = filmReportRepository;
        this.customerCinemaReportRepository = customerCinemaReportRepository;
    }

    @GetMapping
    public String getHomePage(Model model) {
        List<Film> films=filmService.findAllFilms();
        Collections.reverse(films);
        films=films.stream().limit(5).collect(Collectors.toList());
        List <Event> events=eventService.findAllEvents();
        Collections.reverse(events);
        events=events.stream().limit(5).collect(Collectors.toList());
        model.addAttribute("films", films);
        model.addAttribute("events",events);
        model.addAttribute("bodyContent", "home");

        return "master-template";
    }
    @GetMapping("/getFilm/{id}")
    public String getFilm(@PathVariable Long id, Model model) {
        Film film=filmService.getFilmById(id).get();
        model.addAttribute("film", film);
        List<String> genres= List.of(film.getGenre().split(","));
        double r=customerRatesFilmService.avg_rating(film.getId_film());
        Double ra=Double.valueOf(r);
        if(ra==null){
            r=0;
        }
        model.addAttribute("rating",r);
        model.addAttribute("genres", genres);
        model.addAttribute("bodyContent", "film");

        return "master-template";
    }
    @GetMapping("/getEvent/{id}")
    public String getEvent(@PathVariable Long id, Model model) {
        Event event =eventService.getEventById(id).get();
        model.addAttribute("event", event);
        model.addAttribute("bodyContent", "event");

        return "master-template";
    }
    @GetMapping("/getProjections/{id}")
    @Transactional
    public String getProjectionsFromFilm(@PathVariable Long id, Model model) {
        Film film=filmService.getFilmById(id).get();
        model.addAttribute("film",film);
        model.addAttribute("projections",projectionService.getProjectionsForFilms(id.intValue()));
        model.addAttribute("categories",categoryService.findAllCategories());
        model.addAttribute("bodyContent", "projectionsForFilm");

        return "master-template";
    }
    @GetMapping("/getSeats/{id}")
    @Transactional
    public String getSeats(@PathVariable Long id, Model model,@RequestParam Long id_category,@RequestParam Long film) {
        Category category=categoryService.getCategoryById(id_category.intValue()).get();
        Projection projection=projectionService.findById(id.intValue());
        model.addAttribute("film",filmService.getFilmById(film).get());
        model.addAttribute("projection",projection);
        model.addAttribute("category",category);

        List<Seat> seats=seatService.findAllByRoomAndCategory(projection,projectionRoomService.getRoomByProjection(projection.getId_projection()).get(0),category);
        model.addAttribute("seats",seats);
        model.addAttribute("bodyContent", "seats");

        return "master-template";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model)
    {
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model)
    {
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password, Model model, HttpServletRequest request)
    {
//        User user = null;
        try {
           User user=userService.login(username,password);
        System.out.println(user.getFirst_name());
        request.getSession().setAttribute("user", user);
        //            model.addAttribute("user",user);
            return "redirect:/home";

        }catch (UserNotFoundException e)
        {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "login";
        }

    }

    @PostMapping("/register")
    public void register(@RequestParam String username,
                           @RequestParam String first_name,
                           @RequestParam String last_name,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String email,
                           @RequestParam String number,
                           @RequestParam Role role,HttpServletResponse response, HttpSession session) throws IOException {

        System.out.println(username + first_name+ last_name + password + repeatedPassword + email + number + role);
        if(role.equals(Role.ROLE_ADMIN)){
            session.setAttribute("username", username);
            session.setAttribute("first_name", first_name);
            session.setAttribute("last_name", last_name);
            session.setAttribute("password", password);
            session.setAttribute("repeatedPassword", repeatedPassword);
            session.setAttribute("email", email);
            session.setAttribute("number", number);
            response.sendRedirect("/registerWorker");
        }
        else {
            try {
                userService.register(first_name,last_name,username,email,number,password,role);

            }catch (PasswordsDoNotMatchException exception)
            {
//                return "redirect:/register?error=" + exception.getMessage();
            }
            response.sendRedirect("/login");
        }

    }
    @GetMapping("/registerWorker")
    public String getRegisterWorkerPage(Model model){
        model.addAttribute("cinemas",cinemaService.findAllCinemas());
        model.addAttribute("bodyContent","registerWorker");
        return "master-template";
    }
    @PostMapping("/finishRegister")
    public void handleWorkerRegister(Model model, HttpServletResponse response, HttpSession session,
                                     @RequestParam String position, @RequestParam String work_hours_from,
                                     @RequestParam String work_hours_to,@RequestParam Integer id_cinema){
        System.out.println("here?");
        String username = (String) session.getAttribute("username");
        String first_name = (String) session.getAttribute("first_name");
        String last_name = (String) session.getAttribute("last_name");
        String password = (String) session.getAttribute("password");
        String email = (String) session.getAttribute("email");
        String number = (String) session.getAttribute("number");
        Cinema cinema=cinemaService.findCinemaById(id_cinema);
        userService.registerWorker(first_name,last_name,username,email,number,password,position,work_hours_from,work_hours_to,cinema);
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/films")
    @Transactional
    public String getFilmsPage1(Model model,@RequestParam(required = false) Integer id_cinema, @RequestParam(required = false) Integer id_genre){
        model.addAttribute("cinemas",cinemaService.findAllCinemas());
        List<GenreEnum> genres = List.of(GenreEnum.values());
        model.addAttribute("genres", genres);
        List<Film> films = filmService.findAllFilms();
        if (id_cinema!=null) {
            films = filmService.getFilmsFromCinema(id_cinema);
        }
        if ( id_genre != null){
            List<Film> pom= new ArrayList<>();
            for (int i = 0; i < films.size(); i++) {
                if(films.get(i).getGenre().contains(genres.get(id_genre).name().toLowerCase())){
                   pom.add(films.get(i));
                }
            }
            films=pom;
        }
        model.addAttribute("films",films);
        model.addAttribute("bodyContent","films");
        return "master-template";
    }
    @Transactional
    @GetMapping("/projections")
    public String getProjectionsPage(Model model,@RequestParam(required = false) Integer id_cinema)
    {
        model.addAttribute("cinemas",cinemaService.findAllCinemas());
        if (id_cinema!=null) {
            model.addAttribute("films",filmService.getFilmsFromCinemaNow(id_cinema));
        }else{
            List<FilmsReturnTable> pom=new LinkedList<>();
            model.addAttribute("films",filmService.getFilmsNow());
        }
        model.addAttribute("bodyContent","projections");
        return "master-template";
    }
    @GetMapping("/events")
    @Transactional
    public String getEventsPage(Model model,@RequestParam(required = false) Integer id_cinema)
    {
        model.addAttribute("cinemas",cinemaService.findAllCinemas());
        if (id_cinema!=null) {
            model.addAttribute("events",eventService.getEventsFromCinema(id_cinema));
        }else{
            model.addAttribute("events",eventService.getEventsNow());
        }
        model.addAttribute("bodyContent","events");
        return "master-template";
    }
    @GetMapping("/myTickets")
    public  String getMyTicketsPage(Model model,HttpServletRequest request)
    {
        Customer customer=customerService.findByUsername(request.getRemoteUser());
        List<Ticket> tickets = ticketService.findAllByCustomer(customer);
        List<TicketsCancelClass> ticketsCancelClass = new ArrayList<>();
        LocalDateTime oneDayLater = LocalDateTime.now().plus(1, ChronoUnit.DAYS);
        for (int i = 0; i < tickets.size(); i++) {
            if(tickets.get(i).getProjection().getDate_time_start().isAfter(oneDayLater)){
                ticketsCancelClass.add(new TicketsCancelClass(tickets.get(i),true));
            }else{
                ticketsCancelClass.add(new TicketsCancelClass(tickets.get(i),false));
            }
        }
        model.addAttribute("tickets",ticketsCancelClass);
        model.addAttribute("bodyContent","myTickets");
        return "master-template";
    }

    @PostMapping("/cancelTicket/{id}")
    public String getSeats(@PathVariable Long id, Model model) {
        ticketService.delete(id.intValue());
        return "redirect:/myTickets";
    }

    @GetMapping("/addProjection")
    @Transactional
    public  String getAddProjectionPage(Model model)
    {
        model.addAttribute("films",filmService.findAllFilms());
        model.addAttribute("projection_rooms", projectionRoomService.findAllProjectionRooms());
        model.addAttribute("discounts",discountService.getValidDiscounts());
        model.addAttribute("bodyContent","addProjection");
        return "master-template";
    }
    @GetMapping("/addDiscount")
    public  String getAddDiscountPage(Model model)
    {
        model.addAttribute("bodyContent","addDiscount");
        return "master-template";
    }
    @GetMapping("/addEvent")
    public  String getAddEventPage(Model model)
    {
        model.addAttribute("bodyContent","addEvent");
        return "master-template";
    }
    @GetMapping("/addFilm")
    public  String getAddFilmPage(Model model)
    {
        model.addAttribute("bodyContent","addFilm");
        return "master-template";
    }

    @PostMapping("/addD")
    public String saveEvent(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate validity,
                            @RequestParam String type,
                            @RequestParam String code,
                            @RequestParam Integer percent)
    {
        discountService.save(code,type,validity,percent);
        return "redirect:/home";
    }

    @PostMapping("/addP")
    public String saveProjection(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date_time_start,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date_time_end,
                                 @RequestParam String type_of_technology,
                                 @RequestParam Integer id_film,
                                @RequestParam Integer id_room,
                                @RequestParam Integer id_discount)
    {
        projectionService.save(date_time_start,date_time_end,type_of_technology,id_film,id_room,id_discount);
         return "redirect:/home";
    }
    @PostMapping("/addE")
    public String saveEvent(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_date,
                                 @RequestParam String theme,
                                 @RequestParam String duration,
                                @RequestParam String img_url,
                            @RequestParam String repeating)
    {
        eventService.save(start_date,theme,duration,repeating,img_url);
        return "redirect:/home";
    }
    @PostMapping("/addF")
    public String saveFilm(
                            @RequestParam String name,
                            @RequestParam Integer duration,
                            @RequestParam String actors,
                           @RequestParam String genre,
                           @RequestParam String age_category,
                           @RequestParam String url,
                           @RequestParam String director,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_date,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end_date
                           )
    {
        filmService.save(name,duration,actors,genre,age_category,url,director,start_date,end_date);
        return "redirect:/home";
    }

    @GetMapping("/workers")
    public String getWorkersPage(Model model)
    {
        model.addAttribute("workers",workerService.findAllWorkers());
        model.addAttribute("bodyContent", "workers");
        return "master-template";
    }

    @GetMapping("/addEventToCinema")
    public  String getCinemaOrganizesEventPage(Model model)
    {
        model.addAttribute("cinemas",cinemaService.findAllCinemas());
        model.addAttribute("events",eventService.findAllEvents());
        model.addAttribute("bodyContent","addEventToCinema");
        return "master-template";
    }

    @PostMapping("/addCinemaOrganizesEvent")
    public String saveCinemaOrganizesEvent(@RequestParam Integer id_cinema,
                                           @RequestParam Integer id_event)
    {

       cinemaOrganizesEventService.save(id_cinema,id_event);
       return "redirect:/home";
    }
    @GetMapping("/addFilmToCinema")
    public  String getCinemaPlaysFilmPage(Model model)
    {
        model.addAttribute("cinemas",cinemaService.findAllCinemas());
        model.addAttribute("films",filmService.findAllFilms());
        model.addAttribute("bodyContent","addFilmToCinema");
        return "master-template";
    }
    @PostMapping("/addCinemaPlaysFilm")
    public String saveCinemaPlaysFilm(@RequestParam Integer id_cinema,
                                           @RequestParam Integer id_film)
    {
        cinemaPlaysFilmService.save(id_cinema,id_film);
        return "redirect:/home";
    }

    @GetMapping("/getProjection/{id}")
    public String getProjection(@PathVariable Integer id_projection,Model model)
    {
        List<Projection_Room> projectionRooms = null;
        Projection projection=projectionService.findById(id_projection);


        List<ProjectionIsPlayedInRoom> p= projectionIsPlayedInRoomService.getProjectionPlayedInRoom(id_projection);

        model.addAttribute("projection",projection);
        model.addAttribute("p_rooms",projectionRooms);
        model.addAttribute("bodyContent","projectionDetails");
        return "master-template";
    }

    @PostMapping("/makeReservation")
    @Transactional
    public String createTicketForReservation(@RequestParam Long film,@RequestParam Long projection,@RequestParam Long id_seat,@RequestParam String discount,HttpServletRequest request, HttpServletResponse respons)
    {
        Ticket t;
        Customer customer=customerService.findByUsername(request.getRemoteUser());
        Projection projection1=projectionService.findById(projection.intValue());
        if(projection1.getDiscount()!=null && projection1.getDiscount().getCode().equals(discount)){
            t=ticketService.saveWithDiscount(LocalDate.now(),customer,projection1,projection1.getDiscount(),seatService.getSeatById(id_seat.intValue()).get());
            Integer price=ticketService.priceForTicket(t.getId_ticket());
            price+=seatService.getSeatById(id_seat.intValue()).get().getCategory().getExtra_amount();
            price-=(price*projection1.getDiscount().getPercent())/100;
            t.setPrice(price);
        }else{
            t=ticketService.saveWithout(LocalDate.now(),customer,projection1,seatService.getSeatById(id_seat.intValue()).get());
            Integer price=ticketService.priceForTicket(t.getId_ticket());
            price+=seatService.getSeatById(id_seat.intValue()).get().getCategory().getExtra_amount();
            t.setPrice(price);
        }

        return "redirect:/myTickets";
    }
    @PostMapping("/addRating/{id}")
    public String addRating(@RequestParam Long rate,@PathVariable Long id,HttpServletRequest request, HttpServletResponse respons)
    {
        Customer customer=customerService.findByUsername(request.getRemoteUser());
        System.out.println(customer.getFirst_name());
        customerRatesFilmService.addRating(customer.getId_user(),Integer.valueOf(id.intValue()),Integer.valueOf(rate.intValue()));
        return "redirect:/home/getFilm/"+id;
    }
    @GetMapping("/profileWorker")
    public String getWorkerProfile(Model model,HttpServletRequest request)
    {
        Worker worker=workerService.getWorkerByUsername(request.getRemoteUser());
        model.addAttribute("worker",worker);
        model.addAttribute("bodyContent", "profileWorker");
        return "master-template";
    }
    @GetMapping("/profileUser")
    @Transactional
    public String getUserProfile(Model model,HttpServletRequest request)
    {
        Customer customer=customerService.findByUsername(request.getRemoteUser());
        System.out.println(customer.getFirst_name());
        List<Event> events=eventService.getEventsForCustomer(customer.getId_user());
        model.addAttribute("customer",customer);
        model.addAttribute("events",events);
        model.addAttribute("bodyContent", "profileUser");
        return "master-template";
    }
    @PostMapping("/addInterestedEvent/{id}")
    public String addInterestedEvent(@PathVariable Long id,HttpServletRequest request, HttpServletResponse respons)
    {
        Customer customer=customerService.findByUsername(request.getRemoteUser());
        customerIsInterestedInEventService.add(customer.getId_user(),Integer.valueOf(id.intValue()));
        return "redirect:/profileUser";
    }
    @PostMapping("/deleteInterestedEvent/{id}")
    public String deleteInterestedEvent(@PathVariable Long id,HttpServletRequest request, HttpServletResponse respons)
    {
        Customer customer=customerService.findByUsername(request.getRemoteUser());
        Event event=eventService.getEventById(id).get();
        customerIsInterestedInEventService.delete(customer,event);
        return "redirect:/profileUser";
    }

    @GetMapping("/cinemaMonthlyReport")
    public String getCinemaMonthlyInfo(Model model){
        model.addAttribute("data",monthlyCinemaReportRepository.findAll());
        model.addAttribute("bodyContent","monthlyCinemaReport");
        return "master-template";
    }

    @GetMapping("/filmReport")
    public String getFilmInfo(Model model){
        model.addAttribute("data",filmReportRepository.findAll());
        model.addAttribute("bodyContent","filmReport");
        return "master-template";
    }

    @GetMapping("/customerCinemaReport")
    public String getCustomerCinemaInfo(Model model){
        model.addAttribute("data",customerCinemaReportRepository.findAll());
        model.addAttribute("bodyContent","customerCinemaReport");
        return "master-template";
    }
}
