package ua.finalproject.periodicals.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SubscriptionKey implements Serializable {
    private static final int PRIME = 43;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "periodical_id")
    private Long periodicalId;

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
}
