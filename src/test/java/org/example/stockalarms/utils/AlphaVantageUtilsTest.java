package org.example.stockalarms.utils;

import org.example.stockalarms.utils.alphaVantage.AlphaVantageFunction;
import org.example.stockalarms.utils.alphaVantage.AlphaVantageUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * customised class to test the alpha vantage API using the DEMO requests
 */
public class AlphaVantageUtilsTest extends AlphaVantageUtils {
    @Override
    public String getIntraDayStockDataURL(String symbol){
        return "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo";
    }
}
