package ua.finalproject.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.finalproject.periodicals.entity.Periodical;
import ua.finalproject.periodicals.entity.Subscription;
import ua.finalproject.periodicals.entity.User;
import ua.finalproject.periodicals.repository.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription save(User user, Periodical periodical) {
        Subscription subscription = Subscription.builder()
                .user(user)
                .periodical(periodical)
                .startDate(LocalDateTime.now().toLocalDate())
                .build();
        return subscriptionRepository.save(subscription);
    }

    public void delete(User user, Periodical periodical) {
        Subscription subscription = subscriptionRepository.findByUserIdAndPeriodicalId(user.getId(), periodical.getId()).get(0);
        subscriptionRepository.delete(subscription);
    }

    public List<Subscription> findByUserAndPeriodical(User user, Periodical periodical) {
        return subscriptionRepository.findByUserIdAndPeriodicalId(user.getId(), periodical.getId());
    }
}
