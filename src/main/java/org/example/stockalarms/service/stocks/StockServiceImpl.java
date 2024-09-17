package org.example.stockalarms.service.stocks;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.model.Symbol;
import org.example.stockalarms.model.repo.SymbolRepo;
import org.example.stockalarms.service.alphaVantage.AlphaVantageService;
import org.example.stockalarms.utils.Response;
import org.example.stockalarms.utils.alphaVantage.AlphaVantageUtils;
import org.example.stockalarms.utils.alphaVantage.json.TimeSeries;
import org.example.stockalarms.utils.alphaVantage.json.TimeSeriesIntradayResponse;
import org.example.stockalarms.utils.dto.dtos.StockDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements StockService {
    private final AlphaVantageService alphaVantageService;
    private final AlphaVantageUtils alphaVantageUtils;
    private final SymbolRepo symbolRepo;

    @Override
    public Response getAllStockSymbols() {
        List<String> symbols = symbolRepo.findAll().stream().map(Symbol::getName).toList();

        return Response.builder()
                .stockSymbols(symbols)
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .statusCode(HttpStatus.OK.value())
                .message("alphaVantage symbols retrieved with success")
                .build();

    }

    @Override
    public Response getStockData(String symbol) {
        TimeSeriesIntradayResponse response = alphaVantageService.getTimeSeriesIntradayResponse(symbol);
        StockDTO stockDTO = alphaVantageUtils.getStockDTO(response);

        return Response.builder()
                .stockDTO(stockDTO)
                .message("stock retrieved with success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
