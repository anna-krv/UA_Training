package ua.finalproject.periodicals.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.finalproject.periodicals.entity.User;
import ua.finalproject.periodicals.service.AccountService;
import ua.finalproject.periodicals.service.UserService;

import javax.validation.Valid;

@Slf4j
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
        model.addAttribute("loginError", error != null);
        return "authorization/login.html";
    }

    @PostMapping("/login")
    public String tryLogin(@ModelAttribute("user") User user) {
        return userService.checkCredentials(user) ? "redirect:/" : "redirect:/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "authorization/register.html";
    }

    @PostMapping("/register")
    public String tryRegister(@Valid @ModelAttribute("user") User user,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "authorization/register.html";
        }
        try {
            userService.create(user);
            return "redirect:/login";
        } catch (DataIntegrityViolationException ex) {
            log.error(ex.getMessage());
            model.addAttribute("notUniqueUserError", true);
            return "authorization/register.html";
        }
    }
}
