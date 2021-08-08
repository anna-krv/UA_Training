package ua.finalproject.periodicals.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ua.finalproject.periodicals.entity.Account;
import ua.finalproject.periodicals.service.AccountService;
import ua.finalproject.periodicals.service.UserService;

import java.security.Principal;

@Controller
@Slf4j
@RequestMapping("/account")
public class AccountController {
    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public AccountController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping
    public String accountPage(Principal principal,
                              Model model) {
        Account account = userService.findAccountByUsername(principal.getName());
        model.addAttribute("account", account);
        return "reader/account.html";
    }

    @PostMapping
    public String putMoney(@RequestParam("moneyToPut") double moneyToPut,
                           Principal principal,
                           Model model) {
        Account account = userService.findAccountByUsername(principal.getName());
        try {
            Account accountUpdated = accountService.putMoney(account, moneyToPut);
            model.addAttribute("account", accountUpdated);
            model.addAttribute("success", true);
        } catch (IllegalArgumentException ex) {
            model.addAttribute("account", account);
            model.addAttribute("error", true);
        }
        return "reader/account.html";
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleArgumentException(MethodArgumentTypeMismatchException ex,
                                          Principal principal,
                                          Model model) {
        model.addAttribute("formatError", true);
        Account account = userService.findAccountByUsername(principal.getName());
        model.addAttribute("account", account);
        return "reader/account.html";
    }

}
