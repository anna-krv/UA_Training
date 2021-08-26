package ua.finalproject.periodicals.old.controller.command.admin;

import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.controller.command.Command;
import ua.finalproject.periodicals.old.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;

public class AdminPeriodicals implements Command {
    private static final PeriodicalService periodicalService = new PeriodicalService();

    @Override
    public String execute(HttpServletRequest request) {
        int number = RequestUtil.getNumberParam(request);
        request.setAttribute("periodicals", periodicalService.findAll(number));
        request.setAttribute("number", number);
        return "/WEB-INF/admin/periodicals.jsp";
    }
}
