package com.example.moviezone.web;


import com.example.moviezone.model.*;
import com.example.moviezone.model.exceptions.PasswordsDoNotMatchException;

import com.example.moviezone.model.manytomany.ProjectionIsPlayedInRoom;
import com.example.moviezone.repository.ProjectionIsPlayedInRoomRepository;

import com.example.moviezone.model.procedures.FilmsReturnTable;

import com.example.moviezone.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDate;
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


    public HomeController(FilmService filmService, UserService userService, ProjectionService projectionService, EventService eventService, TicketService ticketService, WorkerService workerService, CustomerRatesFilmService customerRatesFilmService, CinemaService cinemaService, CinemaOrganizesEventService cinemaOrganizesEventService, CinemaPlaysFilmService cinemaPlaysFilmService, ProjectionIsPlayedInRoomService projectionIsPlayedInRoomService)
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
    }

    @GetMapping
    public String getHomePage(Model model) {
        List<Film> films=filmService.findAllFilms();
        films=films.stream().limit(5).collect(Collectors.toList());
        List <Event> events=eventService.findAllEvents().stream().limit(5).collect(Collectors.toList());
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
        model.addAttribute("rating",r);
        model.addAttribute("genres", genres);
        model.addAttribute("bodyContent", "film");

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
                        @RequestParam String password,Model model, HttpSession session)
    {
//        User user = null;
//        try {
           User user=userService.login(username,password);
        System.out.println(user.getFirst_name());
//            session.setAttribute("sessionUser",user);
//            model.addAttribute("user",user);
            return "redirect:/home";
//
//        }catch (UserNotFoundException e)
//        {
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", e.getMessage());
//            return "login";
//        }

    }

    @PostMapping()
    public String register(@RequestParam String username,
                           @RequestParam String first_name,
                           @RequestParam String last_name,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String email,
                           @RequestParam String number,
                           @RequestParam Role role)
    {
        try {
           userService.register(first_name,last_name,username,email,number,password,role);
            return "redirect:/login";
        }catch (PasswordsDoNotMatchException exception)
        {
            return "redirect:/register?error=" + exception.getMessage();
        }

    }
    @GetMapping("/films")
    @Transactional
    public String getFilmsPage(Model model){
        model.addAttribute("cinemas",cinemaService.findAllCinemas());
            List<FilmsReturnTable> pom=new LinkedList<>();
            model.addAttribute("films",pom);
            boolean h=pom.isEmpty();
            List<FilmsReturnTable> help=filmService.getFilmsFromCinema(2);
        model.addAttribute("bodyContent","films");
        return "master-template";
    }

    public String getFilmsPage1(Model model,Integer id_cinema){
        model.addAttribute("cinemas",cinemaService.findAllCinemas());
        if (id_cinema!=null) {
            model.addAttribute("films",filmService.getFilmsFromCinema(id_cinema.intValue()));
        }else{
            List<FilmsReturnTable> pom=new LinkedList<>();
            model.addAttribute("films",pom);
        }

        model.addAttribute("bodyContent","films");
        return "master-template";
    }
    @PostMapping("/getFilmsFromCinema")
    public String getFilmsFromCinema(@RequestParam Integer cinema, Model model){
        return getFilmsPage1(model,cinema);
    }
    @GetMapping("/projections")
    public String getProjectionsPage(Model model)
    {
        model.addAttribute("projections",projectionService.findAllProjections());
        model.addAttribute("bodyContent","projections");
        return "master-template";
    }
    @GetMapping("/events")
    public String getEventsPage(Model model)
    {
        model.addAttribute("events",eventService.findAllEvents());
        model.addAttribute("bodyContent","events");
        return "master-template";
    }
    @GetMapping("/myTickets")
    public  String getMyTicketsPage(Model model,HttpSession session)
    {
        model.addAttribute("tickets",ticketService.findAllByCustomer((Customer) session.getAttribute("user")));
        model.addAttribute("bodyContent","myTickets");
        return "master-template";
    }
    @GetMapping("/addProjection")
    public  String getAddProjectionPage(Model model)
    {
        model.addAttribute("films",filmService.findAllFilms());
        model.addAttribute("bodyContent","addProjection");
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

    @PostMapping("/addP")
    public String saveProjection(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_time_start,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_time_end,
                                 @RequestParam String type_of_technology,
                                 @RequestParam Integer id_film)
    {
        projectionService.save(date_time_start,date_time_end,type_of_technology,id_film);
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
    public String createTicketForReservation()
    {
        return "redirect:/myTickets";
    }

}
