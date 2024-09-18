package org.example.stockalarms.integrationTests.configuration;

import org.example.stockalarms.integrationTests.utils.AlphaVantageUtils;
import org.example.stockalarms.service.alphaVantage.AlphaVantageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AlphaVantageServiceITConfiguration {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public AlphaVantageUtils alphaVantageUtils(){
        return new AlphaVantageUtils();
    }
    @Bean
    public AlphaVantageService alphaVantageService(){
        return new AlphaVantageService(alphaVantageUtils(),restTemplate());
    }
}
