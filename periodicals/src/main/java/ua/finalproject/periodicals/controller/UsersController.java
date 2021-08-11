package ua.finalproject.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.finalproject.periodicals.entity.UserNotFoundException;
import ua.finalproject.periodicals.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsersPage(Principal principal,
                               Model model) {
        model.addAttribute("users", userService.findByUsernameNot(principal.getName()));
        return "admin/users.html";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("user", userService.findById(id));
        } catch (UserNotFoundException ex) {
            model.addAttribute("error", true);
        }
        return "admin/aUser.html";
    }

    @PutMapping("/{id}")
    public String changeBlockStatus(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("user", userService.changeBlockStatus(id));
        } catch (UserNotFoundException ex) {
            model.addAttribute("error", true);
        }
        return "admin/aUser.html";
    }
}
