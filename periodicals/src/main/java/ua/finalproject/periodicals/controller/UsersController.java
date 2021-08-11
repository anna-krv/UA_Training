package ua.finalproject.periodicals.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.finalproject.periodicals.exception.UserNotFoundException;
import ua.finalproject.periodicals.service.UserService;

import java.security.Principal;

@Slf4j
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
                               Model model,
                               @RequestParam(value = "number", defaultValue = "0") int number) {

        model.addAttribute("page", userService.findByUsernameNot(principal.getName(), number));

        return "admin/users.html";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("user", userService.getById(id));
        } catch (UserNotFoundException ex) {
            log.error(ex.getMessage());
            model.addAttribute("resourceError", true);
        }
        return "admin/aUser.html";
    }

    @PutMapping("/{id}")
    public String changeBlockStatus(@PathVariable("id") Long id, Model model) {
        try {
            model.addAttribute("user", userService.changeBlockStatus(id));
        } catch (UserNotFoundException ex) {
            log.error(ex.getMessage());
            model.addAttribute("error", true);
        }
        return "admin/aUser.html";
    }
}
