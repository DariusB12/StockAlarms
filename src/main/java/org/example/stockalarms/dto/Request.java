package org.example.stockalarms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.stockalarms.model.user.User;

/**
 * This class encapsulates all the data that can be requested by the client.
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    User user;
}
