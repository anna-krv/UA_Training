package ua.finalproject.periodicals.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Account {
    @Id
    @Column(name = "userId")
    private Long id;

    private BigDecimal balance;

    @OneToOne
    @MapsId
    @JoinColumn(name = "userId")
    private User user;

    public String toString() {
        return "";
    }
}
