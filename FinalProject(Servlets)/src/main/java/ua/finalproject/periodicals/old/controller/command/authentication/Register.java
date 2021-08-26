package ua.finalproject.periodicals.old.controller.command.authentication;

import ua.finalproject.periodicals.old.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class Register implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/login.jsp";
    }
}
