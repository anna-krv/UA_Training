package ua.finalproject.periodicals.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.finalproject.periodicals.entity.Periodical;
import ua.finalproject.periodicals.service.PeriodicalService;
import ua.finalproject.periodicals.service.SubscriptionService;

import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequestMapping("/admin/periodicals")
public class AdminPeriodicalsController {
    private final PeriodicalService periodicalService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public AdminPeriodicalsController(PeriodicalService periodicalService, SubscriptionService subscriptionService) {
        this.periodicalService = periodicalService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping()
    public String allPeriodicalsPage(Model model,
                                     @RequestParam(name = "number", defaultValue = "0") int number) {
        model.addAttribute("page", periodicalService.findAll(number));
        return "admin/periodicals.html";
    }

    @GetMapping("/{id}")
    public String periodicalEditPageById(@PathVariable("id") Long id,
                                         Model model) {
        try {
            Periodical periodical = periodicalService.getById(id);
            model.addAttribute("periodical", periodical);
        } catch (NoSuchElementException ex) {
            log.error(ex.getMessage());
            model.addAttribute("resourceError", true);
        }

        return "admin/editPeriodical.html";
    }

    @PostMapping("/{id}")
    public String periodicalUpdate(@PathVariable("id") Long id,
                                   @ModelAttribute("periodical") Periodical periodical,
                                   Model model) {
        periodicalService.update(id, periodical);
        return "redirect:/admin/periodicals";
    }

    @DeleteMapping("/{id}")
    public String periodicalDelete(@PathVariable("id") Long id) {
        periodicalService.deleteById(id);
        return "redirect:/admin/periodicals";
    }

    @GetMapping("/add")
    public String formToAddPeriodical(Model model) {
        model.addAttribute("periodical", new Periodical());
        return "admin/addPeriodical.html";
    }

    @PostMapping("/add")
    public String addPeriodical(@ModelAttribute("periodical") Periodical periodical,
                                Model model) {
        try {
            periodicalService.save(periodical);
            model.addAttribute("success", true);
            model.addAttribute("periodical", new Periodical());
        } catch (DataIntegrityViolationException ex) {
            log.error(ex.getMessage());
            model.addAttribute("errorNotUnique", true);
        }
        return "admin/addPeriodical.html";
    }

}
