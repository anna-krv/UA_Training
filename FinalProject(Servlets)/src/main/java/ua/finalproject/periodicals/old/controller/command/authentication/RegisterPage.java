package ua.finalproject.periodicals.old.controller.command.authentication;

import ua.finalproject.periodicals.old.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class RegisterPage implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/register.jsp";
    }
}
