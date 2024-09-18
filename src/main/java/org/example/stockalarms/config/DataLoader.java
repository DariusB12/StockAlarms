package org.example.stockalarms.config;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.model.Symbol;
import org.example.stockalarms.model.repo.SymbolRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final SymbolRepo symbolRepo;

    /**
     * Saves all the stock symbols from the .csv file into the DB for efficiently retrievals
     */
    @Override
    public void run(String... args) throws Exception {
        List<Symbol> symbols = getAllStockSymbols();
        symbolRepo.saveAll(symbols);
    }


    /**
     * Retrieve from the .csv file all the alphaVantage symbols
     */
    private List<Symbol> getAllStockSymbols() {
        List<Symbol> symbols = new ArrayList<>();
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
                    symbols.add(Symbol.builder().name(symbol).build());
            }
            return symbols;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
