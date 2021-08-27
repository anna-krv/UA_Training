package ua.finalproject.periodicals.old.controller.command.user;


import ua.finalproject.periodicals.old.controller.ErrorType;
import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.controller.command.Command;
import ua.finalproject.periodicals.old.dao.MoneyAccountException;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.entity.Subscription;
import ua.finalproject.periodicals.old.entity.User;
import ua.finalproject.periodicals.old.service.PeriodicalService;
import ua.finalproject.periodicals.old.service.SubscriptionService;
import ua.finalproject.periodicals.old.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

public class PeriodicalById implements Command {
    private static final PeriodicalService periodicalService = new PeriodicalService();
    private static final SubscriptionService subscriptionService = new SubscriptionService();
    private static final UserService userService = new UserService();
    private static final Logger logger = Logger.getLogger(PeriodicalById.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        Long id = RequestUtil.extractId(request);
        Long userId= (Long)request.getSession().getAttribute("userId");

        try {
            User user = userService.findById(userId).orElseThrow(
                    ()->new NoSuchElementException("no user with id "+userId));
            Periodical periodical = periodicalService.findById(id).orElseThrow(
                    () -> new NoSuchElementException("no periodical with id " + id));
            request.setAttribute("periodical", periodical);
            Optional<Subscription> optional = getSubscription(request, user, periodical);

            request.setAttribute("subscription", optional.orElseThrow(()->new NoSuchElementException(
                    "unable to find subscription for user "+userId+" and periodical "+id)));
            request.setAttribute("error", ErrorType.NONE);
        } catch (MoneyAccountException ex) {
            logger.severe(ex.getMessage());
            request.setAttribute("error", ErrorType.ACCOUNT);
        } catch (NoSuchElementException ex) {
            logger.severe(ex.getMessage());
        } catch (SQLException ex) {
            request.setAttribute("error", ErrorType.OTHER);
        }
        return "/WEB-INF/user/aPeriodical.jsp";
    }

    private Optional<Subscription> getSubscription(HttpServletRequest request, User user, Periodical periodical) throws SQLException {
        Optional<Subscription> optional = Optional.empty();
        switch (RequestUtil.getRequestAction(request)) {
            case SUBSCRIBE:
                optional = Optional.of(subscriptionService.create(user, periodical));
                break;
            case UNSUBSCRIBE:
                subscriptionService.delete(user, periodical);
                break;
            case GET:
                optional = subscriptionService.findByUserAndPeriodical(user, periodical);
                break;
        }
        return optional;
    }
}
