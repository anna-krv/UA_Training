package ua.finalproject.periodicals.old.controller.command.admin;

import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.controller.command.Command;
import ua.finalproject.periodicals.old.entity.User;
import ua.finalproject.periodicals.old.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class AdminUserById implements Command {
    private static final UserService userService = new UserService();
    private static final Logger logger = Logger.getLogger(AdminUserById.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        try {
            request.setAttribute("user", getUser(request));
        } catch (NoSuchElementException ex) {
            logger.severe(ex.getMessage());
            request.setAttribute("resourceError", true);
        }
        return "/WEB-INF/admin/aUser.jsp";
    }

    private User getUser(HttpServletRequest request) {
        Long id = RequestUtil.extractId(request);
        switch(RequestUtil.getRequestAction(request)){
            case CHANGE_BLOCK_STATUS:
                return userService.changeBlockStatus(id).orElseThrow(
                        () -> new NoSuchElementException("no user with id " + id));
            case GET:
            default:
                return userService.findById(id).orElseThrow(
                        () -> new NoSuchElementException("no user with id " + id));
        }
    }
}
