package ua.finalproject.periodicals.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table
public class Subscription {
    @EmbeddedId
    private SubscriptionKey subscriptionKey;// = new SubscriptionKey();

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("periodicalId")
    @JoinColumn(name = "periodical_id")
    private Periodical periodical;

    private LocalDateTime lastPaymentDateTime;
    private LocalDateTime nextPaymentDateTime;
    private int paymentPeriodInDays;
    private boolean status;

    public String getLastPaymentDateTime() {
        return lastPaymentDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }

    public String getNextPaymentDateTime() {
        return nextPaymentDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }

    public String toString() {
        return "user id: " + user.getId() + " periodical id: " + periodical.getId();
    }

    public int hashCode() {
        return subscriptionKey.hashCode();
    }
}

