package ua.finalproject.periodicals.old.controller.command.admin;

import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AdminPeriodicalUpdate implements ua.finalproject.periodicals.old.controller.command.Command {
    private static final PeriodicalService periodicalService = new PeriodicalService();
    @Override
    public String execute(HttpServletRequest request) {
        Long id = RequestUtil.extractId(request, "admin/periodicals/", "/update");
        try {
            Periodical periodical = RequestUtil.extractPeriodicalParam(request);
            periodicalService.update(id, periodical);
        } catch (IllegalArgumentException | SQLException ex) {
            return "redirect:/admin/periodicals/"+id;
        }
        return "redirect:/admin/periodicals";
    }
}
