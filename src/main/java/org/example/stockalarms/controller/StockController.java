package org.example.stockalarms.controller;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.exceptions.customExceptions.AlphaVantageException;
import org.example.stockalarms.utils.Response;
import org.example.stockalarms.service.stocks.StockService;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    /**
     * Retrieves all the alphaVantage symbols from DB
     * @return Response with all alphaVantage symbol names
     */
    @GetMapping
    public Response getAllStockSymbols(){
        return stockService.getAllStockSymbols();
    }

    /**
     * Retrieves the latest stock data available on alpha vantage api
     * @param symbol the stock symbol for which it retrieves the data
     * @return Response containing the stock data information
     */
    @GetMapping("/{symbol}")
    public Response getStockData(@PathVariable String symbol) throws AlphaVantageException {
        return stockService.getStockData(symbol);
    }
}
