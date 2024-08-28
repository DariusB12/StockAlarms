package org.example.stockalarms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.stockalarms.model.user.User;

import java.time.LocalDateTime;

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
    User user;
    Integer statusCode;
    String message;
    LocalDateTime dateTime;
}
