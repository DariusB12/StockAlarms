package org.example.stockalarms.service.stocks;

import org.example.stockalarms.exceptions.customExceptions.AlphaVantageException;
import org.example.stockalarms.utils.Response;

public interface StockService {
    /**
     * Retrieve from the .csv file all the alphaVantage symbols
     * @return Response containing all alphaVantage symbol's names
     */
    Response getAllStockSymbols();

    /**
     * Get the latest alphaVantage data from alpha vantage api using TIME_SERIES_INTRADAY function
     * @param symbol the alphaVantage symbol
     * @return Response with the StockDTO containing the latest alphaVantage data
     */
    Response getStockData(String symbol) throws AlphaVantageException;
}
