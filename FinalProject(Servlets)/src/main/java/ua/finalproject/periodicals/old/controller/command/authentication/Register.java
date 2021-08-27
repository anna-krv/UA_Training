package ua.finalproject.periodicals.old.controller.command.authentication;

import ua.finalproject.periodicals.old.controller.ErrorType;
import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.controller.command.Command;
import ua.finalproject.periodicals.old.entity.User;
import ua.finalproject.periodicals.old.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Register implements Command {
    private static final UserService userService = new UserService();
    private static final Logger logger = Logger.getLogger(Register.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        try {
            User user = RequestUtil.extractUser(request);
            userService.create(user);
            request.setAttribute("error", ErrorType.NONE);
            return "redirect:/loginPage";
        } catch (IllegalArgumentException ex) {
            logger.severe(ex.getMessage());
            request.setAttribute("error", ErrorType.FORMAT);
        } catch (SQLException ex) {
            request.setAttribute("error", ErrorType.NOT_UNIQUE_USER);
        }
        request.setAttribute("user", RequestUtil.extractUserNoValidation(request));
        return "/register.jsp";
    }
}
