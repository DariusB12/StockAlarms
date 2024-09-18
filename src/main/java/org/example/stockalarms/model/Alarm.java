package org.example.stockalarms.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * One user can have multiple alarms, One alarm belongs to one user
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alarms")
public class Alarm{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String symbol;
    private Double initialPrice;
    private Double variance; // [-100,100] percentage
    private Double target; // [-100,100] percentage
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;
    @Override
    public String toString() {
        return "symbol: " + symbol +
                ", initial price: " + initialPrice +
                ", variance: " + variance + "%"+
                ", target: " + target + "%";
    }
}
