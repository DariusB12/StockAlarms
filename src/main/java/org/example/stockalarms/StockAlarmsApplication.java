package org.example.stockalarms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("secretConfiguration.properties")
public class StockAlarmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockAlarmsApplication.class, args);
    }

}
