package ua.finalproject.periodicals.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class SubscriptionKey implements Serializable {
    private static final int PRIME = 43;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "periodical_id")
    private Long periodicalId;

    public int hashCode() {
        return (int) (userId * PRIME + periodicalId);
    }
}
