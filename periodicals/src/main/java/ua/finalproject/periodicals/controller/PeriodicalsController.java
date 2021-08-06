package ua.finalproject.periodicals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.finalproject.periodicals.entity.Periodical;
import ua.finalproject.periodicals.entity.Subscription;
import ua.finalproject.periodicals.entity.User;
import ua.finalproject.periodicals.service.PeriodicalService;
import ua.finalproject.periodicals.service.SubscriptionService;
import ua.finalproject.periodicals.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/periodicals")
public class PeriodicalsController {
    private final UserService userService;
    private final PeriodicalService periodicalService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public PeriodicalsController(UserService userService, PeriodicalService periodicalService, SubscriptionService subscriptionService) {
        this.userService = userService;
        this.periodicalService = periodicalService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
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

    @GetMapping("/subscribed")
    public String periodicalsSubscribedPage(Authentication authentication,
                                            @RequestParam(name = "search", required = false, defaultValue = "any") String title,
                                            @RequestParam(name = "sort", required = false, defaultValue = "title") String sort,
                                            @RequestParam(name = "topic", required = false) List<String> topicsSelected,
                                            Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<Periodical> periodicals = userService.findPeriodicalsByUsernameAndFilterAndSort(
                userDetails.getUsername(),
                title, topicsSelected,
                sort);

        model.addAttribute("periodicals", periodicals);
        model.addAttribute("error", periodicals == null || periodicals.isEmpty());
        model.addAttribute("topics", userService.findAllTopicsByUsername(userDetails.getUsername()));
        model.addAttribute("sort", sort);
        return "reader/periodicals.html";
    }

    @GetMapping("/{id}")
    public String periodicalPageById(@PathVariable("id") Long id,
                                     Authentication authentication,
                                     Model model) {
        Periodical periodical = periodicalService.findById(id).get();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername()).get();
        List<Subscription> subscriptions = subscriptionService.findByUserAndPeriodical(user, periodical);

        model.addAttribute("periodical", periodical);
        model.addAttribute("alreadySubscribed", subscriptions != null && !subscriptions.isEmpty());
        return "reader/onePeriodical.html";
    }

    @GetMapping("/{id}/subscribe")
    public String periodicalSubscribe(@PathVariable("id") Long id,
                                      Authentication authentication,
                                      Model model) {
        Periodical periodical = periodicalService.findById(id).get();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername()).get();

        subscriptionService.save(user, periodical);
        model.addAttribute("alreadySubscribed", true);
        model.addAttribute("periodical", periodical);
        return "reader/onePeriodical.html";
    }

    @GetMapping("/{id}/unsubscribe")
    public String periodicalUnSubscribe(@PathVariable("id") Long id,
                                        Authentication authentication,
                                        Model model) {
        Periodical periodical = periodicalService.findById(id).get();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername()).get();

        subscriptionService.delete(user, periodical);
        model.addAttribute("alreadySubscribed", false);
        model.addAttribute("periodical", periodical);
        return "reader/onePeriodical.html";
    }
}
