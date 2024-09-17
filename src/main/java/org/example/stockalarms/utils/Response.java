package org.example.stockalarms.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.stockalarms.dto.AlarmDTO;
import org.example.stockalarms.dto.StockDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class intend to encapsulate any response given by the backend side to the client.
 * It provides all the necessary data that a client can request
 * along with a status code, a response message and a boolean flag.
 * (easier for frontend side to detect success or failure using this flag)
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    List<String> stockSymbols;
    StockDTO stockDTO;
    AlarmDTO alarmDTO;
    Integer statusCode;
    String message;
    LocalDateTime dateTime;
}
