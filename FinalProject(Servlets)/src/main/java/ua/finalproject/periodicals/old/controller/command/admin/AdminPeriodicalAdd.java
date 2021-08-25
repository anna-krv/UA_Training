package ua.finalproject.periodicals.old.controller.command.admin;

import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class AdminPeriodicalAdd implements ua.finalproject.periodicals.old.controller.command.Command {
    private static final PeriodicalService periodicalService = new PeriodicalService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            Periodical periodical = RequestUtil.extractPeriodicalParam(request);
            periodicalService.save(periodical);
            session.setAttribute("success", true);
            session.setAttribute("periodical", new Periodical());
        } catch (IllegalArgumentException ex) {
            session.setAttribute("error", true);
            return "redirect:/admin/periodicals/addPage";
        } catch (SQLException ex) {
            session.setAttribute("errorNotUnique", true);
            return "redirect:/admin/periodicals/addPage";
        }
        return "admin/addPeriodical.html";
    }
}

