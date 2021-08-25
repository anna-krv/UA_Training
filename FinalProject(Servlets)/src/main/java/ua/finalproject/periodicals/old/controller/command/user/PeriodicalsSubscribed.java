package ua.finalproject.periodicals.old.controller.command.user;

import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.controller.command.Command;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.service.Criteria;
import ua.finalproject.periodicals.old.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PeriodicalsSubscribed implements Command {
    private static final PeriodicalService periodicalService = new PeriodicalService();

    @Override
    public String execute(HttpServletRequest request) {
        String title = request.getParameter("search");
        String fieldForSort = request.getParameter("sort");
        String[] topicsSelectedArr = request.getParameterValues("topic");
        List<String> allTopics = periodicalService.findTopics();
        List<String> topicsSelected = topicsSelectedArr != null ? Arrays.asList(topicsSelectedArr) : allTopics;
        int number = RequestUtil.getNumberParam(request);
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        Criteria criteria = new Criteria(fieldForSort, number, title, topicsSelected, Optional.of(userId));
        List<Periodical> periodicals = periodicalService.findByCriteria(criteria);
        List<String> topicsForUser = periodicalService.findAllTopicsByUser(userId);

        session.setAttribute("periodicals", periodicals);
        session.setAttribute("searchError", periodicals.isEmpty());
        session.setAttribute("number", number);
        session.setAttribute("topics", topicsForUser);
        session.setAttribute("topicsSelected", topicsSelected != null ? topicsSelected : topicsForUser);
        session.setAttribute("sort", fieldForSort);
        session.setAttribute("search", title);
        session.setAttribute("personalPage", true);
        return "/WEB-INF/user/periodicals.jsp";
    }
}
