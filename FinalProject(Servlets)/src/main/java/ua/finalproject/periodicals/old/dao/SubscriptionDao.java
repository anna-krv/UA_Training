package ua.finalproject.periodicals.old.dao;

import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.entity.Subscription;
import ua.finalproject.periodicals.old.entity.SubscriptionKey;
import ua.finalproject.periodicals.old.entity.User;

import java.util.Optional;

public interface SubscriptionDao extends GenericDao<Subscription, SubscriptionKey>{
    Optional<Subscription> findByUserAndPeriodical(User user, Periodical periodical);

    void deleteByPeriodicalId(Long periodicalId);
}
