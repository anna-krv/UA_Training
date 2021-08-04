package ua.finalproject.periodicals.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Periodical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    // Divide by 100 to get real price.
    private int price;
    private Topic topic;

    public double getPrice() {
        return price / 100.;
    }

    public void setPrice(double price) {
        this.price = (int) price * 100;
    }
}