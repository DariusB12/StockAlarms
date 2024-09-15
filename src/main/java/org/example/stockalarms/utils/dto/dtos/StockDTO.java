package org.example.stockalarms.utils.dto.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockDTO {
    private String symbol;
    private Double currentPrice;
    private String currency;
}
