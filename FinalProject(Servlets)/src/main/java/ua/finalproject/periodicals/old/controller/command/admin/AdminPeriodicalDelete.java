package ua.finalproject.periodicals.old.controller.command.admin;

import ua.finalproject.periodicals.old.controller.ErrorType;
import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.service.PeriodicalService;
import ua.finalproject.periodicals.old.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;

public class AdminPeriodicalDelete implements ua.finalproject.periodicals.old.controller.command.Command {
    private static final PeriodicalService periodicalService = new PeriodicalService();
    private static final SubscriptionService subscriptionService = new SubscriptionService();

    @Override
    public String execute(HttpServletRequest request) {
        Long id =RequestUtil.extractId(request);
        subscriptionService.deleteByPeriodicalId(id);
        periodicalService.deleteById(id);
        request.setAttribute("error", ErrorType.NONE);
        return "redirect:/admin/periodicals";
    }

}
