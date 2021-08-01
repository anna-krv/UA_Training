package ua.finalproject.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/")
    public String mainPage() {
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
}
