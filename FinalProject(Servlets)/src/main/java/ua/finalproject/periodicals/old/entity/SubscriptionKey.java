package ua.finalproject.periodicals.old.entity;

public class SubscriptionKey {
    private static final int PRIME = 43;

    private Long periodicalId;
    private Long userId;

    public SubscriptionKey(Long periodicalId, Long userId) {
        this.periodicalId = periodicalId;
        this.userId = userId;
    }


    public int hashCode() {
        return (int) (userId * PRIME + periodicalId);
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof SubscriptionKey)) {
            return false;
        }
        SubscriptionKey other = (SubscriptionKey) o;
        if (other.hashCode() != hashCode()) {
            return false;
        }
        return other.userId == userId && other.periodicalId == periodicalId;
    }

    public String toString() {
        return "Key: " + hashCode();
    }

    public Long getPeriodicalId() {
        return periodicalId;
    }

    public Long getUserId() {
        return userId;
    }
}
