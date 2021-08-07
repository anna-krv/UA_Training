package ua.finalproject.periodicals.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table
public class Subscription {
    @EmbeddedId
    private SubscriptionKey subscriptionKey = new SubscriptionKey();
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("periodicalId")
    @JoinColumn(name = "periodical_id")
    private Periodical periodical;

    @Column(name = "start_date")
    private LocalDate startDate;

    public String toString() {
        return ";user id: " + user.getId();
    }

    public int hashCode() {
        return subscriptionKey.hashCode();
    }
}

