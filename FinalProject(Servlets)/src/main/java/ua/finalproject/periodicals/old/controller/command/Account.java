package ua.finalproject.periodicals.old.controller.command;

import ua.finalproject.periodicals.old.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.logging.Logger;

public class Account implements Command {
    private static final UserService userService = new UserService();
    private static final Logger logger = Logger.getLogger(Account.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        String moneyToPutStr= request.getParameter("moneyToPut");
        if (moneyToPutStr!=null){
            putMoney(session, moneyToPutStr, userId);
        }

        session.setAttribute("balance", userService.findById(userId).get().getBalance());
        return "/WEB-INF/account.jsp";
    }

    private void putMoney(HttpSession session, String moneyToPutStr, Long userId) {
        try {
            BigDecimal moneyToPut=new BigDecimal(moneyToPutStr);
            if (moneyToPut.compareTo(BigDecimal.ZERO)<=0){
                throw new IllegalArgumentException("Money amount is negative: "+moneyToPut+", but should be >0.");
            }
            session.setAttribute("success", userService.putMoney(userId, moneyToPut));
        } catch (NumberFormatException ex){
            logger.severe(ex.getMessage());
            session.setAttribute("formatError", true);
        } catch (IllegalArgumentException ex) {
            logger.severe(ex.getMessage());
            session.setAttribute("moneyError", true);
        }
    }
}
