package ua.finalproject.periodicals.old.controller.command.admin;

import ua.finalproject.periodicals.old.controller.ErrorType;
import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AdminPeriodicalUpdate implements ua.finalproject.periodicals.old.controller.command.Command {
    private static final PeriodicalService periodicalService = new PeriodicalService();
    @Override
    public String execute(HttpServletRequest request) {
        Long id = RequestUtil.extractId(request);
        try {
            Periodical periodical = RequestUtil.extractPeriodical(request);
            periodicalService.update(id, periodical);
            request.setAttribute("error", ErrorType.NONE);
        } catch (IllegalArgumentException ex) {
            request.setAttribute("error", ErrorType.FORMAT);
        } catch(SQLException ex){
            request.setAttribute("error", ErrorType.OTHER);
        }
       return "redirect:/admin/periodicals/"+id;
    }
}
