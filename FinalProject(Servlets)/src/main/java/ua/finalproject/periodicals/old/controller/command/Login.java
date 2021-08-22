package ua.finalproject.periodicals.old.controller.command;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command{

    @Override
    public String execute(HttpServletRequest request) {



        return "/index.jsp";
    }
}
