package ua.finalproject.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String login() {
        return "login.html";
    }

    @PostMapping("/login")
    public String tryLogin() {//@ModelAttribute("user") User user){
        User tmp = new User();
        tmp.setLogin("anna");
        Optional<User> user = userService.findByLogin(tmp);//.getPassword().equals()
        System.out.println(user.orElse(tmp).toString());
        return "main.html";
    }

    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

    @PostMapping("register")
    public String tryRegister() {
        //if (userRepository)..
        return "redirect:/login";
    }
}
