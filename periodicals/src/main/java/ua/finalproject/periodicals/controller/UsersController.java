package ua.finalproject.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.finalproject.periodicals.entity.User;
import ua.finalproject.periodicals.service.UserService;

import java.security.Principal;
import java.util.NoSuchElementException;

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
    public String changeBlockStatusIfRequiredAndShowUser(
            @PathVariable("id") Long id,
            @RequestParam(value = "block", required = false) String block,
            Model model) {
        try {
            User user = block != null ? userService.changeBlockStatus(id) : userService.findById(id).get();
            model.addAttribute("user", user);
        } catch (NoSuchElementException ex) {
            model.addAttribute("error", true);
        }
        return "admin/aUser.html";
    }
}
