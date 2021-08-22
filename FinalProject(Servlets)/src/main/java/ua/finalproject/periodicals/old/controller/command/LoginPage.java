package ua.finalproject.periodicals.old.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public class LoginPage implements Command{
    private static final Logger logger = Logger.getLogger(LoginPage.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("I AM STUPID BECAUSE I DO NOT REFRESH !!");
        return "/login.jsp";
    }
}
