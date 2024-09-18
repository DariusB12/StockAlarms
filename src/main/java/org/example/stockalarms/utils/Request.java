package org.example.stockalarms.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.stockalarms.dto.AlarmDTO;
import org.example.stockalarms.dto.UserDTO;

/**
 * Class used for encapsulating the information about different types of requests (except for LogIn and Register which are separate classes)
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    public UserDTO user;
    public AlarmDTO alarm;
}
