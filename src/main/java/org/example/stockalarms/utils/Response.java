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
 * along with a status code, a response message
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    List<AlarmDTO> alarms;
    List<String> stockSymbols;
    StockDTO stock;
    AlarmDTO alarm;
    Integer statusCode;
    String message;
    LocalDateTime dateTime;
}
