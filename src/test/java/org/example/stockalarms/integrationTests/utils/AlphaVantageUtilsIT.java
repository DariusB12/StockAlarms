package org.example.stockalarms.integrationTests.utils;

import org.example.stockalarms.utils.alphaVantage.AlphaVantageUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * customised class to test the alpha vantage API using the DEMO requests
 */
public class AlphaVantageUtilsIT extends AlphaVantageUtils {
    @Value("${alpha.vantage.api.key.time.series.intraday.demo}")
    private String timeSeriesIntradayDemo;

    /**
     * returns the url for accessing the demo data for alpha vantage time series intraday
     * @param symbol doesn't matter, for override purposes
     */
    @Override
    public String getIntraDayStockDataURL(String symbol){
        return timeSeriesIntradayDemo;
    }
}
