package org.example.stockalarms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlarmDTO {
    private Integer id; // if multiple alarms are added, identify them by id
    private String symbol;
    private Double initialPrice;
    private Double variance; // [-100,100] percentage
    private Double target; // [-100,100] percentage
    private Boolean active;
    private String email; // in order to know the owner of the alarm
}
