package ua.finalproject.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.finalproject.periodicals.entity.MoneyAccountException;
import ua.finalproject.periodicals.entity.Periodical;
import ua.finalproject.periodicals.entity.Subscription;
import ua.finalproject.periodicals.entity.User;
import ua.finalproject.periodicals.repository.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    private static final int DEFAULT_PAYMENT_PERIOD_IN_MINUTES = 1;
    private final SubscriptionRepository subscriptionRepository;
    private final AccountService accountService;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, AccountService accountService) {
        this.subscriptionRepository = subscriptionRepository;
        this.accountService = accountService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = MoneyAccountException.class)
    public void chargeMoneyForSubscription(Subscription subscription) throws MoneyAccountException {
        User user = subscription.getUser();
        Periodical periodical = subscription.getPeriodical();
        subscription.setLastPaymentDateTime(LocalDateTime.now());
        subscription.setNextPaymentDateTime(LocalDateTime.now().plusMinutes(subscription.getPaymentPeriodInMin()));
        save(subscription);

        accountService.chargeMoney(user.getAccount(), periodical.getPrice());
    }

    private Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = MoneyAccountException.class)
    public Subscription save(User user, Periodical periodical) throws MoneyAccountException {
        Optional<Subscription> optionalSubscription = findByUserAndPeriodical(user, periodical);
        if (optionalSubscription.isPresent()) {
            return optionalSubscription.get();
        }

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setPeriodical(periodical);
        subscription.setLastPaymentDateTime(LocalDateTime.now());
        subscription.setPaymentPeriodInMin(DEFAULT_PAYMENT_PERIOD_IN_MINUTES);
        subscription.setNextPaymentDateTime(LocalDateTime.now().plusMinutes(DEFAULT_PAYMENT_PERIOD_IN_MINUTES));

        Subscription savedSubscription = subscriptionRepository.save(subscription);

        accountService.chargeMoney(user.getAccount(), periodical.getPrice());

        return savedSubscription;
    }

    public void delete(User user, Periodical periodical) {
        Subscription subscription = subscriptionRepository.findByUserIdAndPeriodicalId(user.getId(), periodical.getId()).get(0);
        subscriptionRepository.delete(subscription);
    }

    public Optional<Subscription> findByUserAndPeriodical(User user, Periodical periodical) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserIdAndPeriodicalId(user.getId(),
                periodical.getId());
        Optional<Subscription> subscription = Optional.empty();
        if (subscriptions != null && !subscriptions.isEmpty()) {
            subscription = Optional.ofNullable(subscriptions.get(0));
        }
        return subscription;
    }

    public void deleteByPeriodicalId(Long id) {
        subscriptionRepository.deleteByPeriodicalIdNative(id);
    }


    public List<Subscription> findByNextPaymentDateTimeLessThanEqual(LocalDateTime now) {
        return subscriptionRepository.findByNextPaymentDateTimeLessThanEqual(LocalDateTime.now());
    }
}
