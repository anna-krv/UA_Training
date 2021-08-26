package ua.finalproject.periodicals.old.controller.command.authentication;

import ua.finalproject.periodicals.old.controller.command.Command;
import ua.finalproject.periodicals.old.entity.User;
import ua.finalproject.periodicals.old.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.logging.Logger;

public class Login implements Command {
    private static final Logger logger = Logger.getLogger(Login.class.getName());
    private static final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        logger.info("Attempt to log user with username: "+username);

        Optional<User> user = userService.findByCredentials(username, password);

        if (user.isPresent()){
            request.getSession().setAttribute("role", user.get().getAuthority());
            request.getSession().setAttribute("userId", user.get().getId());
            return "/WEB-INF/index.jsp";
        }

        logger.info("invalid credentials for username: "+ username);
        return "/login.jsp";

    }
}
