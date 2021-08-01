package ua.finalproject.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.finalproject.periodicals.entity.User;
import ua.finalproject.periodicals.service.UserService;

import java.util.Optional;

@Controller
public class AuthorizationController {
    private final UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "authorization/login.html";
    }

    @PostMapping("/login")
    public String tryLogin(@ModelAttribute("user") User user) {
        Optional<User> userFound = userService.findByLogin(user);
        if (userFound.isPresent() && userFound.get().getPassword().equals(user.getPassword())) {
            return "home.html";
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "authorization/register.html";
    }

    @PostMapping("register")
    public String tryRegister(@ModelAttribute("user") User user) {
        userService.save(user);
        System.out.println("Added a user!!");
        return "redirect:/login";
    }
}
