package ua.finalproject.periodicals.old.controller.command.admin;

import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.controller.command.Command;
import ua.finalproject.periodicals.old.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AdminUsersPage implements Command {
    private static final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        int number = RequestUtil.getNumberParam(request);

        request.setAttribute("users", userService.findByIdNot(
                (Long) request.getSession().getAttribute("userId"), number));
        request.setAttribute("number", number);
        return "/WEB-INF/admin/users.jsp";
    }
}

