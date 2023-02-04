package com.example.moviezone.web;


import com.example.moviezone.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

private final FilmService filmService;

    public HomeController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("bodyContent", "home");
        return "master-template";
    }

    

}
