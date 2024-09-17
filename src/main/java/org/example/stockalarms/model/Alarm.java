package org.example.stockalarms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * One user can have multiple alarms, One alarm belongs to one user
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alarms")
public class Alarm implements Runnable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private Double initialPrice;
    private Double variance; // [-100,100] percentage
    private Double target; // [-100,100] percentage
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    @Override
    public void run() {

    }

    @Override
    public String toString() {
        return "symbol: " + symbol +
                ", initialPrice: " + initialPrice +
                ", variance: " + variance +
                ", target: " + target;
    }
}
