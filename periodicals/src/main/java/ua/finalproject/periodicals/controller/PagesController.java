package ua.finalproject.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/")
    public String mainPage() {
        System.out.println("get index");
        return "index.html";
    }

    @GetMapping("/UA")
    public String setUA() {

        System.out.println("set language to UA");
        return "index.html";
    }

    @GetMapping("/EN")
    public String setEN() {

        System.out.println("set language to EN");
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

}
