package ua.finalproject.periodicals.old.controller.command.user;

import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.service.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class Periodicals extends PeriodicalsAbstract implements ua.finalproject.periodicals.old.controller.command.Command {

    @Override
    public String execute(HttpServletRequest request) {
        Criteria criteria = RequestUtil.extractCriteria(request);
        criteria.setUserId(Optional.empty());
        return execute(request, criteria, periodicalService.findTopics());
    }
}

