package ua.finalproject.periodicals.old.controller.command.user;

import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.controller.command.Command;
import ua.finalproject.periodicals.old.service.Criteria;

import javax.servlet.http.HttpServletRequest;

public class PeriodicalsSubscribed extends PeriodicalsAbstract implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Criteria criteria = RequestUtil.extractCriteria(request);
        return execute(request, criteria,
                periodicalService.findAllTopicsByUser(criteria.getUserId().get()));
    }
}
