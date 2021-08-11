package ua.finalproject.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.finalproject.periodicals.entity.*;
import ua.finalproject.periodicals.repository.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    private static final int DEFAULT_PAYMENT_PERIOD_IN_DAYS = 30;
    private final SubscriptionRepository subscriptionRepository;
    private final AccountService accountService;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, AccountService accountService) {
        this.subscriptionRepository = subscriptionRepository;
        this.accountService = accountService;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = MoneyAccountException.class)
    public Subscription save(User user, Periodical periodical) throws MoneyAccountException {
        Optional<Subscription> optionalSubscription = findByUserAndPeriodical(user, periodical);
        if (optionalSubscription.isPresent()) {
            return optionalSubscription.get();
        }

        Subscription subscription = build(user, periodical);
        save(subscription);
        accountService.chargeMoney(user.getAccount(), periodical.getPrice());

        return subscription;
    }

    private Subscription build(User user, Periodical periodical) {
        return Subscription
                .builder()
                .subscriptionKey(new SubscriptionKey(user.getId(), periodical.getId()))
                .user(user)
                .periodical(periodical)
                .status(true)
                .lastPaymentDateTime(LocalDateTime.now())
                .paymentPeriodInDays(DEFAULT_PAYMENT_PERIOD_IN_DAYS)
                .nextPaymentDateTime(LocalDateTime.now().plusDays(DEFAULT_PAYMENT_PERIOD_IN_DAYS))
                .build();
    }

    private Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = MoneyAccountException.class)
    public void chargeMoneyForSubscription(Subscription subscription) throws MoneyAccountException {
        User user = subscription.getUser();
        Periodical periodical = subscription.getPeriodical();
        subscription.setLastPaymentDateTime(LocalDateTime.now());
        subscription.setNextPaymentDateTime(LocalDateTime.now().plusMinutes(subscription.getPaymentPeriodInDays()));
        save(subscription);

        accountService.chargeMoney(user.getAccount(), periodical.getPrice());
    }

    public void delete(User user, Periodical periodical) {
        subscriptionRepository.deleteById(new SubscriptionKey(user.getId(), periodical.getId()));
    }

    public Optional<Subscription> findByUserAndPeriodical(User user, Periodical periodical) {
        return findByUserIdAndPeriodicalId(user.getId(), periodical.getId());
    }

    public Optional<Subscription> findByUserIdAndPeriodicalId(Long userId, Long periodicalId) {
        return subscriptionRepository.findById(new SubscriptionKey(userId, periodicalId));
    }

    public void deleteByPeriodicalId(Long id) {
        subscriptionRepository.deleteByPeriodicalIdNative(id);
    }


    public List<Subscription> findByNextPaymentDateTimeLessThanEqual(LocalDateTime now) {
        return subscriptionRepository.findByNextPaymentDateTimeLessThanEqual(LocalDateTime.now());
    }

    public List<Subscription> findByPeriodicalId(Long periodicalId) {
        return subscriptionRepository.findByPeriodicalId(periodicalId);
    }

    public Subscription deactivate(Subscription subscription) {
        subscription.setStatus(false);
        return save(subscription);
    }
}
