package ua.finalproject.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.finalproject.periodicals.entity.User;
import ua.finalproject.periodicals.service.AccountService;
import ua.finalproject.periodicals.service.UserService;

import java.util.Optional;

@Controller
public class AuthorizationController {
    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public AuthorizationController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("error", error != null);
        return "authorization/login.html";
    }

    @PostMapping("/login")
    public String tryLogin(@ModelAttribute("user") User user) {
        System.out.println("TRY TO LOGIN _-----------------------");
        Optional<User> userFound = userService.findByUsername(user);
        if (userFound.isPresent() && userFound.get().getPassword().equals(user.getPassword())) {
            return "redirect:/readers/" + userFound.get().getId();
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "authorization/register.html";
    }

    @PostMapping("/register")
    public String tryRegister(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/login";
    }
}
