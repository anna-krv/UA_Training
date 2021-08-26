package ua.finalproject.periodicals.old.controller.command.admin;

import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AdminPeriodicalAdd implements ua.finalproject.periodicals.old.controller.command.Command {
    private static final PeriodicalService periodicalService = new PeriodicalService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            Periodical periodical = RequestUtil.extractPeriodicalParam(request);
            periodicalService.save(periodical);
            request.setAttribute("success", true);
            request.setAttribute("periodical", new Periodical());
        } catch (IllegalArgumentException ex) {
            request.setAttribute("error", true);
            return "redirect:/admin/periodicals/addPage";
        } catch (SQLException ex) {
            request.setAttribute("errorNotUnique", true);
            return "redirect:/admin/periodicals/addPage";
        }
        return "admin/addPeriodical.html";
    }
}

