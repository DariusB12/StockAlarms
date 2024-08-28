package org.example.stockalarms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.regex.Pattern;

@SpringBootApplication
public class StockAlarmsApplication {
    public static void main(String[] args) {
//        String  pss = "^%^$@";
//        System.out.println(pss.matches(".*[A-Z].*"));
//        System.out.println(pss.matches(".*[a-z].*"));
//        System.out.println(pss.matches(".*\\W.*"));
        SpringApplication.run(StockAlarmsApplication.class, args);
    }

}
