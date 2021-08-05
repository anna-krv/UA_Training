package ua.finalproject.periodicals.controller;

import lombok.extern.slf4j.Slf4j;
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
import ua.finalproject.periodicals.entity.Periodical;
import ua.finalproject.periodicals.service.AccountService;
import ua.finalproject.periodicals.service.PeriodicalService;
import ua.finalproject.periodicals.service.UserService;

import javax.validation.constraints.Min;
import java.util.List;

@Controller
@Validated
@Slf4j
public class ReadersController {
    private final UserService userService;
    private final AccountService accountService;
    private final PeriodicalService periodicalService;

    @Autowired
    public ReadersController(UserService userService, AccountService accountService, PeriodicalService periodicalService) {
        this.userService = userService;
        this.accountService = accountService;
        this.periodicalService = periodicalService;
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


    @GetMapping("/periodicals")
    public String periodicalsPage(@RequestParam(name = "search", required = false) String title,
                                  @RequestParam(name = "sort", required = false, defaultValue = "title") String sort,
                                  @RequestParam(name = "topic", required = false) List<String> topicsSelected,
                                  Model model) {
        List<Periodical> periodicals;

        periodicals = periodicalService.find(title, sort, topicsSelected);
        model.addAttribute("periodicals", periodicals);
        model.addAttribute("error", periodicals == null || periodicals.isEmpty());
        model.addAttribute("topics", periodicalService.findAllTopics());
        model.addAttribute("sort", sort);
        return "reader/periodicals.html";
    }
}
