package ua.finalproject.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.finalproject.periodicals.entity.Account;
import ua.finalproject.periodicals.service.AccountService;
import ua.finalproject.periodicals.service.UserService;

import javax.validation.constraints.Min;

@Controller
@Validated
public class ReadersController {
    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public ReadersController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/account")
    public String accountPage(Authentication authentication,
                              Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account account = userService.findAccountByUsername(userDetails.getUsername());
        model.addAttribute("account", account);
        return "reader/account.html";
    }

    @PostMapping("/account")
    public String putMoney(Authentication authentication,
                           @RequestParam("moneyToPut") @Min(0) double moneyToPut,
                           Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account account = userService.findAccountByUsername(userDetails.getUsername());
        Account accountUpdated = accountService.putMoney(account, moneyToPut);
        model.addAttribute("success", true);
        model.addAttribute("account", accountUpdated);
        return "reader/account.html";
    }
}
