package ua.finalproject.periodicals.old.controller.command.admin;

import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.controller.command.Command;
import ua.finalproject.periodicals.old.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminUsersPage implements Command {
    private static final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        int number = RequestUtil.getNumberParam(request);
        HttpSession session = request.getSession();

        session.setAttribute("users", userService.findByIdNot((Long) session.getAttribute("userId"), number));
        session.setAttribute("number", number);
        return "/WEB-INF/admin/users.jsp";
    }
}

