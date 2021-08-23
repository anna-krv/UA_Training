package ua.finalproject.periodicals.old.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Periodicals implements ua.finalproject.periodicals.old.controller.command.Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/periodicals.jsp";
    }
}
