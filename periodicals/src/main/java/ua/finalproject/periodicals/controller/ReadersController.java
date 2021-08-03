package ua.finalproject.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/readers")
public class ReadersController {
    @GetMapping("/{id}")
    public String homePage(@PathVariable("id") int id) {
        System.out.println("home for " + id);
        return "home.html";
    }
}
