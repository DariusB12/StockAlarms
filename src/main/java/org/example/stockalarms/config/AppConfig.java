package org.example.stockalarms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    /**
     * Creates a RestTemplate which is used to send requests to the 3rd library Alpha Vantage
     */
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
