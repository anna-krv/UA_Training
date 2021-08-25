package ua.finalproject.periodicals.old.controller.command;


import ua.finalproject.periodicals.old.controller.PathUtil;
import ua.finalproject.periodicals.old.dao.MoneyAccountException;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.entity.Subscription;
import ua.finalproject.periodicals.old.entity.User;
import ua.finalproject.periodicals.old.service.PeriodicalService;
import ua.finalproject.periodicals.old.service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

public class PeriodicalById implements Command {
    private static final PeriodicalService periodicalService = new PeriodicalService();
    private static final SubscriptionService subscriptionService = new SubscriptionService();
    private static final Logger logger = Logger.getLogger(PeriodicalById.class.getName());

    enum RequestType {GET, SUBSCRIBE, UNSUBSCRIBE}
    ;

    @Override
    public String execute(HttpServletRequest request) {
        Long id = extractId(request);
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");
        try {
            Periodical periodical = periodicalService.findById(id).orElseThrow(
                    () -> new NoSuchElementException("no periodical with id " + id));
            session.setAttribute("periodical", periodical);
            Optional<Subscription> optional = getSubscription(request, user, periodical);

            session.setAttribute("subscription", optional.orElse(null));
            session.setAttribute("success", true);
        } catch (MoneyAccountException ex) {
            logger.severe(ex.getMessage());
            session.setAttribute("accountError", true);
        } catch (NoSuchElementException ex) {
            logger.severe(ex.getMessage());
            session.setAttribute("resourceError", true);
        } catch (SQLException ex) {
            session.setAttribute("error", true);
        }
        return "/WEB-INF/aPeriodical.jsp";
    }

    private Optional<Subscription> getSubscription(HttpServletRequest request, User user, Periodical periodical) throws SQLException {
        Optional<Subscription> optional = Optional.empty();
        switch (getType(request)) {
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

    private RequestType getType(HttpServletRequest request) {
        String path = PathUtil.getPath(request.getRequestURI());
        if (path.endsWith("unsubscribe")) {
            return RequestType.UNSUBSCRIBE;
        }
        if (path.endsWith("subscribe")) {
            return RequestType.SUBSCRIBE;
        }
        return RequestType.GET;
    }

    private Long extractId(HttpServletRequest request) {
        String id = PathUtil.getPath(request.getRequestURI())
                .replace("periodicals/", "")
                .replaceFirst("/.*", "");
        return Long.valueOf(id);
    }
}
