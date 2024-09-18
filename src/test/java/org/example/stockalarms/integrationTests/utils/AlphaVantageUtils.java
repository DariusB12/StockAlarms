package org.example.stockalarms.integrationTests.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * customised class to test the alpha vantage API using the DEMO requests
 */
@Service
public class AlphaVantageUtils extends org.example.stockalarms.utils.alphaVantage.AlphaVantageUtils {
    @Value("${TIME_SERIES_INTRADAY_DEMO_KEY}")
    private String timeSeriesIntradayDemo;

    /**
     * returns the url for accessing the demo data for alpha vantage time series intraday
     * Symbol should be null for getting the right demo api url
     * For testing the error responses, add any string to the symbol param to get an invalid url
     * @param symbol doesn't matter, for override purposes
     */
    @Override
    public String getIntraDayStockDataURL(String symbol){
        if(symbol == null)
            return timeSeriesIntradayDemo;
         else
            return timeSeriesIntradayDemo.substring(0,timeSeriesIntradayDemo.length()-4); // remove "demo" word to get an invalid url
    }
}
