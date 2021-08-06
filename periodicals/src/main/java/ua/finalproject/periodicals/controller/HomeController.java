package ua.finalproject.periodicals.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Authentication authentication,
                           Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "home/guest.html";
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("user", userDetails);
        return "home/home.html";
    }
}
