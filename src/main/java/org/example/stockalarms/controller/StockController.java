package org.example.stockalarms.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.stockalarms.utils.Response;
import org.example.stockalarms.service.stocks.StockService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    /**
     * Retrieves all the alphaVantage symbols from .csv file
     * @return Response with all alphaVantage symbol names
     */
    @GetMapping
    public Response getAllStockSymbols(){
        return stockService.getAllStockSymbols();
    }

    @GetMapping("/{symbol}")
    public Response getStockData(@PathVariable String symbol){
        return stockService.getStockData(symbol);
    }
}
