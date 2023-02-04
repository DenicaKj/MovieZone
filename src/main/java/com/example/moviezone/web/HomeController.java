package com.example.moviezone.web;


import com.example.moviezone.model.Customer;
import com.example.moviezone.model.User;
import com.example.moviezone.model.exceptions.UserNotFoundException;
import com.example.moviezone.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class HomeController {

private final FilmService filmService;
private final UserService userService;
private final ProjectionService projectionService;
private final EventService eventService;
private final TicketService ticketService;
private final WorkerService workerService;

    public HomeController(FilmService filmService, UserService userService, ProjectionService projectionService, EventService eventService, TicketService ticketService, WorkerService workerService) {
        this.filmService = filmService;
        this.userService = userService;
        this.projectionService = projectionService;
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.workerService = workerService;
    }

    @GetMapping({"/","/home"})
    public String getHomePage(Model model) {
        model.addAttribute("bodyContent", "home");
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
    public String login(@RequestParam String username,@RequestParam String password,Model model, HttpSession session)
    {
        User user = null;
        try {
            user=userService.login(username,password);
            session.setAttribute("sessionUser",user);
            model.addAttribute("user",user);
            return "redirect:/home";

        }catch (UserNotFoundException e)
        {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "login";
        }

    }

    @PostMapping("register")
    public String register(@RequestParam String username, @RequestParam String first_name, @RequestParam String last_name,
                         @RequestParam String password, @RequestParam String repeatedPassword,
                         @RequestParam String email, @RequestParam String number,
                         @RequestParam String role)
    {
        User user = null;
            user=userService.register(first_name,last_name,username,email,number,password,repeatedPassword,role);
            return "redirect:/login";
    }

    @GetMapping("/films")
    public String getFilmsPage(Model model){
        model.addAttribute("films",filmService.findAllFilms());
        model.addAttribute("bodyContent","films");
        return "master-template";
    }

    @GetMapping("/projections")
    public String getProjectionsPage(Model model)
    {
        model.addAttribute("projections",projectionService.findAllAvailableProjections(LocalDate.now()));
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
                            @RequestParam String repeating)
    {
        eventService.save(start_date,theme,duration,repeating);
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
}
