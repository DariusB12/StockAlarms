package org.example.stockalarms.service.alphaVantage;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.utils.alphaVantage.AlphaVantageUtils;
import org.example.stockalarms.utils.alphaVantage.json.TimeSeriesIntradayResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * class used to handle all the interactions with the external AlphaVantage API
 */
@RequiredArgsConstructor
@Service
public class AlphaVantageService {
    private final AlphaVantageUtils alphaVantageUtils;
    private final RestTemplate restTemplate;
    /**
     * Sends an HTTP GET request to alpha vantage Api to get intraday data about specified symbol for interval of 5 minutes
     */
    public TimeSeriesIntradayResponse getTimeSeriesIntradayResponse(String symbol){
        String url = alphaVantageUtils.getIntraDayStockDataURL(symbol);
        return restTemplate.getForEntity(url, TimeSeriesIntradayResponse.class).getBody();
    }
}
