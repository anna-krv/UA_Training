package ua.finalproject.periodicals.old.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Subscription {
    private SubscriptionKey subscriptionKey;
    private Periodical periodical;
    private User user;
    private LocalDateTime lastPaymentDateTime;
    private LocalDateTime nextPaymentDateTime;
    private int paymentPeriodInDays;
    private boolean status;

    public Subscription( Periodical periodical, User user,
                        LocalDateTime lastPaymentDateTime, LocalDateTime nextPaymentDateTime,
                        int paymentPeriodInDays, boolean status) {
        this.subscriptionKey = new SubscriptionKey(periodical.getId(), user.getId());
        this.periodical = periodical;
        this.user = user;
        this.lastPaymentDateTime = lastPaymentDateTime;
        this.nextPaymentDateTime = nextPaymentDateTime;
        this.paymentPeriodInDays = paymentPeriodInDays;
        this.status = status;
    }

    public Subscription() {

    }

    public String getLastPaymentDateTime(DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter == null){dateTimeFormatter=DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);}
        return lastPaymentDateTime.format(dateTimeFormatter);
    }

    public String getNextPaymentDateTime(DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter == null){dateTimeFormatter=DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);}
        return nextPaymentDateTime.format(dateTimeFormatter);
    }

    public int hashCode() {
        return subscriptionKey.hashCode();
    }

    public SubscriptionKey getSubscriptionKey() {
        return subscriptionKey;
    }

    public void setSubscriptionKey(SubscriptionKey subscriptionKey) {
        this.subscriptionKey = subscriptionKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Periodical getPeriodical() {
        return periodical;
    }

    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
    }
    public LocalDateTime getLastPaymentDateTime() {
        return lastPaymentDateTime;
    }

    public LocalDateTime getNextPaymentDateTime() {
        return nextPaymentDateTime;
    }
    public void setLastPaymentDateTime(LocalDateTime lastPaymentDateTime) {
        this.lastPaymentDateTime = lastPaymentDateTime;
    }

    public void setNextPaymentDateTime(LocalDateTime nextPaymentDateTime) {
        this.nextPaymentDateTime = nextPaymentDateTime;
    }

    public int getPaymentPeriodInDays() {
        return paymentPeriodInDays;
    }

    public void setPaymentPeriodInDays(int paymentPeriodInDays) {
        this.paymentPeriodInDays = paymentPeriodInDays;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionKey=" + subscriptionKey +
                ", user=" + user +
                ", periodical=" + periodical +
                ", lastPaymentDateTime=" + lastPaymentDateTime +
                ", nextPaymentDateTime=" + nextPaymentDateTime +
                ", paymentPeriodInDays=" + paymentPeriodInDays +
                ", status=" + status +
                '}';
    }
}
