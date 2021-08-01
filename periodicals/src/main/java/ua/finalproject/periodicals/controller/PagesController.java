package ua.finalproject.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/")
    public String mainPage() {
        return "index.html";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home.html";
    }
}
