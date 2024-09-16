package org.example.stockalarms.utils.alphaVantage.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeSeries {
    @JsonProperty("1. open")
    private Double open;

    @JsonProperty("2. high")
    private Double high;

    @JsonProperty("3. low")
    private Double low;

    @JsonProperty("4. close")
    private Double close;

    @JsonProperty("5. volume")
    private Double volume;
}

