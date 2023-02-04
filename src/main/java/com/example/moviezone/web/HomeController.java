package com.example.moviezone.web;


import com.example.moviezone.model.User;
import com.example.moviezone.model.exceptions.UserNotFoundException;
import com.example.moviezone.service.EventService;
import com.example.moviezone.service.FilmService;
import com.example.moviezone.service.ProjectionService;
import com.example.moviezone.service.UserService;
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

    public HomeController(FilmService filmService, UserService userService, ProjectionService projectionService, EventService eventService) {
        this.filmService = filmService;
        this.userService = userService;
        this.projectionService = projectionService;
        this.eventService = eventService;
    }

    @GetMapping
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

}
