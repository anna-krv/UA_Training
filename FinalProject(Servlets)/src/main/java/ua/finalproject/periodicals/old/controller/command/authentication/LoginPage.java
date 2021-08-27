package ua.finalproject.periodicals.old.controller.command.authentication;

import ua.finalproject.periodicals.old.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public class LoginPage implements Command {
    private static final Logger logger = Logger.getLogger(LoginPage.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        return "/login.jsp";
    }
}
