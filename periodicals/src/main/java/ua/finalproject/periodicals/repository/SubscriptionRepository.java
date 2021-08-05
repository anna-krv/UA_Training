package ua.finalproject.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.finalproject.periodicals.entity.Subscription;
import ua.finalproject.periodicals.entity.SubscriptionKey;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionKey> {
    Subscription save(Subscription subscription);

    void delete(Subscription subscription);

    List<Subscription> findByUserIdAndPeriodicalId(Long id, Long id1);
}
