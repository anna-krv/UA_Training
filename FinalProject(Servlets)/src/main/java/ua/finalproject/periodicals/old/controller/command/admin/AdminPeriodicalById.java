package ua.finalproject.periodicals.old.controller.command.admin;

import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.controller.command.Command;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class AdminPeriodicalById implements Command {

    private static final PeriodicalService periodicalService = new PeriodicalService();
    private static final Logger logger = Logger.getLogger(AdminPeriodicalById.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        Long id = RequestUtil.extractId(request);
        try {
            Periodical periodical = periodicalService.findById(id).orElseThrow(
                    () -> new NoSuchElementException("no periodical with id " + id));
            request.setAttribute("periodical", periodical);
        } catch (NoSuchElementException ex) {
            logger.severe(ex.getMessage());
            request.setAttribute("resourceError", true);
        }
        return "/WEB-INF/admin/editPeriodical.jsp";
    }
}
