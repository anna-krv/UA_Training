package ua.finalproject.periodicals.old.controller.command;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public class Logout implements Command {
    private static final Logger logger = Logger.getLogger(Logout.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        logger.info("finish session "+request.getSession().getId());
        return "/index.jsp";
    }
}
