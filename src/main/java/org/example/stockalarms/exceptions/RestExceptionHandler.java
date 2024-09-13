package org.example.stockalarms.exceptions;

import org.example.stockalarms.dto.Response;
import org.example.stockalarms.exceptions.customExceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.example.stockalarms.exceptions.customExceptions.UserException.*;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class used to handle all the exceptions thrown by REST controllers
 */
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        return Response.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .message("email already taken")
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .build();
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleValidationException(ValidationException ex){
        return Response.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .build();
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response handleBadCredentialsException(BadCredentialsException ex){
        return Response.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message(ex.getMessage())
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .build();
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response handleUsernameNotFoundException(UsernameNotFoundException ex){
        return Response.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message("email or password incorrect")
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .build();
    }
}
