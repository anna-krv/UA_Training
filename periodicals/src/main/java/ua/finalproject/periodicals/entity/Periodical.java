package ua.finalproject.periodicals.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "periodical")
public class Periodical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    // Divide by 100 to get real price.
    private int price;
    private String topic;
    @OneToMany(mappedBy = "periodical")
    Set<Subscription> subscriptions;

    public double getPrice() {
        return price / 100.;
    }

    public void setPrice(double price) {
        this.price = (int) price * 100;
    }

    public String toString() {
        return "";
    }
}
