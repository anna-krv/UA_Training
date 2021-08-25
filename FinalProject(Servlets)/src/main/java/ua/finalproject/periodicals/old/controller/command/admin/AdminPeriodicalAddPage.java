package ua.finalproject.periodicals.old.controller.command.admin;

import ua.finalproject.periodicals.old.entity.Periodical;

import javax.servlet.http.HttpServletRequest;

public class AdminPeriodicalAddPage implements ua.finalproject.periodicals.old.controller.command.Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("periodical", new Periodical());
        return "/WEB-INF/admin/addPeriodical.jsp";
    }
}
