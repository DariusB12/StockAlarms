package org.example.stockalarms.integrationTests.configuration;

import org.example.stockalarms.integrationTests.utils.AlphaVantageUtilsTest;
import org.example.stockalarms.service.alphaVantage.AlphaVantageService;
import org.example.stockalarms.service.stocks.StockServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AlphaVantageServiceTestConfiguration {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public AlphaVantageUtilsTest alphaVantageUtilsTest(){
        return new AlphaVantageUtilsTest();
    }
    @Bean
    public AlphaVantageService alphaVantageService(){
        return new AlphaVantageService(alphaVantageUtilsTest(),restTemplate());
    }
}
