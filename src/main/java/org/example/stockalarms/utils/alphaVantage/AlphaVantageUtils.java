package org.example.stockalarms.utils.alphaVantage;

import org.example.stockalarms.utils.alphaVantage.json.TimeSeries;
import org.example.stockalarms.utils.alphaVantage.json.TimeSeriesIntradayResponse;
import org.example.stockalarms.utils.dto.dtos.StockDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AlphaVantageUtils {
    @Value("${alpha.vantage.api.key}")
    private String API_KEY;
    private final String BASE_URL="https://www.alphavantage.co/query?";

    public String getIntraDayStockDataURL(String symbol){
        return BASE_URL +
                "function=" +
                AlphaVantageFunction.TIME_SERIES_INTRADAY +
                "&symbol=" +
                symbol +
                "&interval=" +
                "5min" +
                "&apikey=" +
                API_KEY;
    }
    /**
     * Retrieves from an intraday response the latest stock data (StockDTO properties)
     */
    public StockDTO getStockDTO(TimeSeriesIntradayResponse response) {
        //take the first entry (the latest data for the stock)
        Map.Entry<String, TimeSeries> entry = response.getTimeSeries().entrySet().iterator().next();

        return StockDTO.builder()
                .symbol(response.getMetaData().getSymbol())
                .currentPrice(entry.getValue().getClose())
                .build();
    }
}
