package ua.finalproject.periodicals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/users")
public class UsersController {
    @GetMapping
    public String getUsersPage() {
        return "admin/users.html";
    }
}
