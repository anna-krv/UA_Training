package ua.finalproject.periodicals.old.controller.command.authentication;

import ua.finalproject.periodicals.old.controller.command.Command;
import ua.finalproject.periodicals.old.entity.User;

import javax.servlet.http.HttpServletRequest;

public class RegisterPage implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("user", new User());
        return "/register.jsp";
    }
}
