package ua.finalproject.periodicals.old.controller.command.user;

import ua.finalproject.periodicals.old.controller.ErrorType;
import ua.finalproject.periodicals.old.controller.command.Command;
import ua.finalproject.periodicals.old.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Account implements Command {
    private static final UserService userService = new UserService();
    private static final Logger logger = Logger.getLogger(Account.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");

        String moneyToPutStr= request.getParameter("moneyToPut");
        if (moneyToPutStr!=null){
            putMoney(request, moneyToPutStr, userId);
        }

        request.setAttribute("balance", userService.findById(userId).get().getBalance());
        return "/WEB-INF/user/account.jsp";
    }

    private void putMoney(HttpServletRequest request, String moneyToPutStr, Long userId) {
        try {
            BigDecimal moneyToPut=new BigDecimal(moneyToPutStr);
            if (moneyToPut.compareTo(BigDecimal.ZERO)<=0){
                throw new IllegalArgumentException("Money amount is negative: "+moneyToPut+", but should be >0.");
            }
            userService.putMoney(userId, moneyToPut);
            request.setAttribute("error", ErrorType.NONE);
        } catch (NumberFormatException ex){
            logger.severe(ex.getMessage());
            request.setAttribute("error", ErrorType.FORMAT);
        } catch (IllegalArgumentException ex) {
            logger.severe(ex.getMessage());
            request.setAttribute("error", ErrorType.MONEY);
        } catch (SQLException ex) {
            request.setAttribute("error", ErrorType.OTHER);
        }
    }
}
