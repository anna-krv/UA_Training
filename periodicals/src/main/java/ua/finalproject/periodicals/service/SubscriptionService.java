package ua.finalproject.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.finalproject.periodicals.entity.Periodical;
import ua.finalproject.periodicals.entity.Subscription;
import ua.finalproject.periodicals.entity.SubscriptionKey;
import ua.finalproject.periodicals.entity.User;
import ua.finalproject.periodicals.exception.MoneyAccountException;
import ua.finalproject.periodicals.repository.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final AccountService accountService;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, AccountService accountService) {
        this.subscriptionRepository = subscriptionRepository;
        this.accountService = accountService;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = MoneyAccountException.class)
    public Subscription saveForUserAndPeriodical(User user, Periodical periodical) throws MoneyAccountException {
        Optional<Subscription> optionalSubscription = findByUserAndPeriodical(user, periodical);
        if (optionalSubscription.isPresent()) {
            return optionalSubscription.get();
        }

        return saveAndCharge(build(user, periodical));
    }

    public Subscription saveAndCharge(Subscription subscription) throws MoneyAccountException {
        subscriptionRepository.save(subscription);
        accountService.chargeMoney(subscription.getUser().getAccount(), subscription.getPeriodical().getPrice());
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
                .paymentPeriodInDays(AppConstants.PAYMENT_PERIOD_IN_DAYS)
                .nextPaymentDateTime(LocalDateTime.now().plusDays(AppConstants.PAYMENT_PERIOD_IN_DAYS))
                .build();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = MoneyAccountException.class)
    public Subscription chargeMoneyForSubscription(Subscription subscription) throws MoneyAccountException {
        return saveAndCharge(updateDates(subscription));
    }


    public Subscription updateDates(Subscription subscription) {
        subscription.setLastPaymentDateTime(LocalDateTime.now());
        subscription.setNextPaymentDateTime(LocalDateTime.now().plusMinutes(subscription.getPaymentPeriodInDays()));
        return subscription;
    }


    public Optional<Subscription> findByUserAndPeriodical(User user, Periodical periodical) {
        return findByUserIdAndPeriodicalId(user.getId(), periodical.getId());
    }

    public Optional<Subscription> findByUserIdAndPeriodicalId(Long userId, Long periodicalId) {
        return subscriptionRepository.findById(new SubscriptionKey(userId, periodicalId));
    }

    public List<Subscription> findByNextPaymentDateTimeLessThanEqual(LocalDateTime now) {
        return subscriptionRepository.findByNextPaymentDateTimeLessThanEqual(LocalDateTime.now());
    }

    public List<Subscription> findByPeriodicalId(Long periodicalId) {
        return subscriptionRepository.findByPeriodicalId(periodicalId);
    }

    public void delete(User user, Periodical periodical) {
        subscriptionRepository.deleteById(new SubscriptionKey(user.getId(), periodical.getId()));
    }

    public void deleteByPeriodicalId(Long id) {
        subscriptionRepository.deleteByPeriodicalIdNative(id);
    }

    public Subscription deactivate(Subscription subscription) {
        subscription.setStatus(false);
        return subscriptionRepository.save(subscription);
    }
}
