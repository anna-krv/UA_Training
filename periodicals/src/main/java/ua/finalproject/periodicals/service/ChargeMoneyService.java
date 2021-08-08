package ua.finalproject.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.finalproject.periodicals.entity.MoneyAccountException;
import ua.finalproject.periodicals.entity.Subscription;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChargeMoneyService {
    private final SubscriptionService subscriptionService;

    @Autowired
    public ChargeMoneyService(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void ChargeMoney() {
        List<Subscription> subscriptionsToBeCharged = subscriptionService.findByNextPaymentDateTimeLessThanEqual(
                LocalDateTime.now());
        for (Subscription subscription : subscriptionsToBeCharged) {
            try {
                subscriptionService.chargeMoneyForSubscription(subscription);
            } catch (MoneyAccountException ex) {
                //todo mark subscription inactive
            }
            System.out.println(subscription);
        }
    }
}
