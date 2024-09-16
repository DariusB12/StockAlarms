package org.example.stockalarms.utils.alphaVantage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AlphaVantageUtils {
    @Value("${alpha.vantage.api.key}")
    private static String API_KEY;
    private static final String BASE_URL="https://www.alphavantage.co/query?";

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
}
