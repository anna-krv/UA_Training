package ua.finalproject.periodicals.old.controller.command;

import javax.servlet.http.HttpServletRequest;

public class PeriodicalsSubscribed implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return  "WEB_INF/periodicalsSubscribed.jsp";
    }
}
