package org.example.stockalarms.service.stocks;

import lombok.RequiredArgsConstructor;
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
public class StockServiceImpl implements StockService{
    private final AlphaVantageUtils alphaVantageUtils;
    private final RestTemplate restTemplate;
    @Override
    public Response getAllStockSymbols() {
        List<String> symbols = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File("src/main/resources/nasdaq_stock_symbols.csv"));
            boolean firstLine = true;
            while(scanner.hasNextLine()){
                if(firstLine) {
                    scanner.nextLine();
                    firstLine = false;
                    continue;
                }
                String line = scanner.nextLine();
                String symbol = line.split(",")[0];
                if(Pattern.compile("^[A-Za-z0-9]+$").matcher(symbol).matches())
                    symbols.add(symbol);
            }
            return Response.builder()
                    .stockSymbols(symbols)
                    .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                    .statusCode(HttpStatus.OK.value())
                    .message("alphaVantage symbols retrieved with success")
                    .build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response getStockData(String symbol) {
        TimeSeriesIntradayResponse response = getTimeSeriesIntradayResponse(symbol);
        StockDTO stockDTO = getStockDTO(response);

        return Response.builder()
                .stockDTO(stockDTO)
                .message("stock retrieved with success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    /**
     * Retrieves from an intraday response the latest stock data (StockDTO properties)
     */
    private StockDTO getStockDTO(TimeSeriesIntradayResponse response) {
        Map.Entry<String,TimeSeries> entry = response.getTimeSeries().entrySet().iterator().next();

        return StockDTO.builder()
                .symbol(response.getMetaData().getSymbol())
                .currentPrice(entry.getValue().getClose())
                .build();
    }

    /**
     * Sends an HTTP GET request to alpha vantage Api to get intraday data about specified symbol for interval of 5 minutes
     */
    private TimeSeriesIntradayResponse getTimeSeriesIntradayResponse(String symbol){
        String url = alphaVantageUtils.getIntraDayStockDataURL(symbol);
        return restTemplate.getForEntity(url, TimeSeriesIntradayResponse.class).getBody();
    }
}
