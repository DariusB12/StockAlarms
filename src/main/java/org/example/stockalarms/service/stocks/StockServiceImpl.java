package org.example.stockalarms.service.stocks;

import org.example.stockalarms.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

@Service
public class StockServiceImpl implements StockService{
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
                    .message("stock symbols retrieved with success")
                    .build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response getStockData(String symbol) {
        return null;
    }
}
