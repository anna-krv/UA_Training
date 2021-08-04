package ua.finalproject.periodicals.entity;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table
public class Account {
    @Id
    @Column(name = "userId")
    private Long id;
    // Divide by 100 to get real amount of money.
    private int balance;
    @OneToOne
    @MapsId
    @JoinColumn(name = "userId")
    private User user;

    public double getBalance() {
        return balance / 100.;
    }

    public void setBalance(double balance) {
        this.balance = (int) (balance * 100);
    }

    public String toString() {
        return "";
    }
}
