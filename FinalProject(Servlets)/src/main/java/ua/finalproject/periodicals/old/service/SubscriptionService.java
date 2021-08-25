package ua.finalproject.periodicals.old.service;

import ua.finalproject.periodicals.old.config.Configurations;
import ua.finalproject.periodicals.old.config.Constants;
import ua.finalproject.periodicals.old.dao.DaoFactory;
import ua.finalproject.periodicals.old.dao.SubscriptionDao;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.entity.Subscription;
import ua.finalproject.periodicals.old.entity.SubscriptionKey;
import ua.finalproject.periodicals.old.entity.User;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

public class SubscriptionService {
    private final SubscriptionDao subscriptionDao = DaoFactory.getInstance().createSubscriptionDao();
    private final static int PAYMENT_PERIOD_IN_DAYS = Integer.valueOf(Configurations.getProperty(Constants.PAYMENT_PERIOD_IN_DAYS));

    private Logger logger = Logger.getLogger(SubscriptionService.class.getName());

    public Subscription create(User user, Periodical periodical) throws SQLException {
        Subscription subscription = buildForUserAndPeriodical(user, periodical);
        subscriptionDao.create(subscription);
        return subscription;
    }

    private Subscription buildForUserAndPeriodical(User user, Periodical periodical) {
        return new Subscription(periodical, user, LocalDateTime.now(),
                LocalDateTime.now().plusDays(PAYMENT_PERIOD_IN_DAYS), PAYMENT_PERIOD_IN_DAYS, true);

    }

    public void delete(User user, Periodical periodical) {
        subscriptionDao.delete(new SubscriptionKey(periodical.getId(), user.getId()));
    }

    public Optional<Subscription> findByUserAndPeriodical(User user, Periodical periodical) {
        return subscriptionDao.findByUserAndPeriodical(user, periodical);
    }

    public void deleteByPeriodicalId(Long periodicalId) {
        subscriptionDao.deleteByPeriodicalId(periodicalId);
    }
}
