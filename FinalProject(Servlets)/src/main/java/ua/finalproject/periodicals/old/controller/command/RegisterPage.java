package ua.finalproject.periodicals.old.controller.command;

import javax.servlet.http.HttpServletRequest;

public class RegisterPage implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/register.jsp";
    }
}