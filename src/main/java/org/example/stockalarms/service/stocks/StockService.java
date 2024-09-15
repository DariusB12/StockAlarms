package org.example.stockalarms.service.stocks;

import org.example.stockalarms.utils.Response;

public interface StockService {
    /**
     * Retrieve from the .csv file all the stock symbols
     * @return Response containing all stock symbol's names
     */
    Response getAllStockSymbols();

    /**
     * Get the latest stock data from alpha vantage api using TIME_SERIES_INTRADAY function
     * @param symbol the stock symbol
     * @return Response with the StockDTO containing the latest stock data
     */
    Response getStockData(String symbol);
}
