package ua.finalproject.periodicals.old.controller.command.user;

import ua.finalproject.periodicals.old.controller.ErrorType;
import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.service.Criteria;
import ua.finalproject.periodicals.old.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

abstract public class PeriodicalsAbstract {
    protected static final PeriodicalService periodicalService = new PeriodicalService();

    public String execute(HttpServletRequest request, Criteria criteria, List<String> topicsForChoice) {
        if (criteria.getTopics().isEmpty()) {
            criteria.setTopics(topicsForChoice);
        }

        List<Periodical> periodicals = periodicalService.findByCriteria(criteria);
        request.setAttribute("periodicals", periodicals);
        if (periodicals.isEmpty()) {
            request.setAttribute("error", ErrorType.SEARCH);
        }
        RequestUtil.setRequestAttrFromCriteria(request, criteria);
        request.setAttribute("topics", topicsForChoice);
        return "/WEB-INF/user/periodicals.jsp";
    }
}
