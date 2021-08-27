package ua.finalproject.periodicals.old.controller.command.admin;

import ua.finalproject.periodicals.old.controller.ErrorType;
import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AdminPeriodicalAdd implements ua.finalproject.periodicals.old.controller.command.Command {
    private static final PeriodicalService periodicalService = new PeriodicalService();
private static final Logger logger = Logger.getLogger(AdminPeriodicalAdd.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        try {
            Periodical periodical = RequestUtil.extractPeriodical(request);
            request.setAttribute("periodical", periodical);
            periodicalService.save(periodical);

            request.setAttribute("error", ErrorType.NONE);
            request.setAttribute("periodical", new Periodical());
        } catch (IllegalArgumentException ex) {
            logger.severe(ex.getMessage());
            request.setAttribute("error", ErrorType.FORMAT);
        } catch (SQLException ex) {
            request.setAttribute("error", ErrorType.NOT_UNIQUE);
        }
        return "/WEB-INF/admin/addPeriodical.jsp";
    }
}

