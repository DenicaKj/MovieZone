package com.example.moviezone.web;


import com.example.moviezone.model.User;
import com.example.moviezone.model.exceptions.UserNotFoundException;
import com.example.moviezone.service.FilmService;
import com.example.moviezone.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {

private final FilmService filmService;
private final UserService userService;

    public HomeController(FilmService filmService, UserService userService) {
        this.filmService = filmService;
        this.userService = userService;
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

}
