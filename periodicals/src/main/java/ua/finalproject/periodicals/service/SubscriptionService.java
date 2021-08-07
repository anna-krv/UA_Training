package ua.finalproject.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.finalproject.periodicals.entity.*;
import ua.finalproject.periodicals.repository.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final AccountService accountService;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, AccountService accountService) {
        this.subscriptionRepository = subscriptionRepository;
        this.accountService = accountService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = MoneyAccountException.class)
    public Subscription save(User user, Periodical periodical) throws MoneyAccountException, SubscriptionException {
        if (checkByUserAndPeriodical(user, periodical)) {
            throw new SubscriptionException();
        }
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setPeriodical(periodical);
        subscription.setStartDate(LocalDateTime.now().toLocalDate());
        Subscription savedSubscription = subscriptionRepository.save(subscription);

        accountService.chargeMoney(user.getAccount(), periodical.getPrice());

        return savedSubscription;
    }

    public boolean checkByUserAndPeriodical(User user, Periodical periodical) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserIdAndPeriodicalId(
                user.getId(), periodical.getId());
        return subscriptions != null && !subscriptions.isEmpty();
    }


    public void delete(User user, Periodical periodical) {
        Subscription subscription = subscriptionRepository.findByUserIdAndPeriodicalId(user.getId(), periodical.getId()).get(0);
        subscriptionRepository.delete(subscription);
    }

    public List<Subscription> findByUserAndPeriodical(User user, Periodical periodical) {
        return subscriptionRepository.findByUserIdAndPeriodicalId(user.getId(), periodical.getId());
    }

    public void deleteByPeriodicalId(Long id) {
        subscriptionRepository.deleteByPeriodicalIdNative(id);
    }
}
