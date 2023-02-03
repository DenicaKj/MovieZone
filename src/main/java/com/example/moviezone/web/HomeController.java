package com.example.moviezone.web;


import com.example.moviezone.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

private final FilmService filmService;

    public HomeController(FilmService filmService) {
        this.filmService = filmService;
    }


}
