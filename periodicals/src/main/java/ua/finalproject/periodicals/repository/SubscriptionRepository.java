package ua.finalproject.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.finalproject.periodicals.entity.Subscription;
import ua.finalproject.periodicals.entity.SubscriptionKey;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionKey> {
    Subscription save(Subscription subscription);

    void delete(Subscription subscription);

    List<Subscription> findByUserIdAndPeriodicalId(Long id, Long id1);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DELETE FROM subscription where periodical_id=:id", nativeQuery = true)
    void deleteByPeriodicalIdNative(@Param("id") Long id);
}
