package ua.finalproject.periodicals.old.controller.command.user;

import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.service.Criteria;
import ua.finalproject.periodicals.old.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class Periodicals implements ua.finalproject.periodicals.old.controller.command.Command {
    private static final PeriodicalService periodicalService = new PeriodicalService();

    @Override
    public String execute(HttpServletRequest request) {
        String title = request.getParameter("search");
        String fieldForSort = request.getParameter("sort");
        String[] topicsSelectedArr=request.getParameterValues("topic");
        List<String> allTopics = periodicalService.findTopics();
        List<String> topicsSelected =topicsSelectedArr!=null ?Arrays.asList(topicsSelectedArr ):allTopics;
        String numberStr = request.getParameter("number");
        int number = Integer.valueOf(numberStr == null ? "0" : numberStr);

        Criteria criteria = new Criteria(fieldForSort, number, title, topicsSelected, Optional.empty());
        List<Periodical> periodicals = periodicalService.findByCriteria(criteria);

        request.setAttribute("periodicals", periodicals);
        request.setAttribute("topics", allTopics);
        request.setAttribute("topicsSelected", topicsSelected != null ? topicsSelected : allTopics);
        request.setAttribute("sort", fieldForSort);
        request.setAttribute("search", title);
        request.setAttribute("personalPage", false);
        request.setAttribute("number", number);
        return "/WEB-INF/user/periodicals.jsp";
    }
}

