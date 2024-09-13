package org.example.stockalarms.controller;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.dto.requests.LogInRequest;
import org.example.stockalarms.dto.requests.RegisterRequest;
import org.example.stockalarms.dto.Response;
import org.example.stockalarms.exceptions.customExceptions.UserException.*;
import org.example.stockalarms.exceptions.customExceptions.ValidationException;
import org.example.stockalarms.service.user.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private final UserService userServiceImpl;

    @PostMapping("signUp")
    public Response registerUser(@RequestBody RegisterRequest registerRequest) throws ValidationException, UserAlreadyExistsException {
        return userServiceImpl.registerUser(registerRequest);
    }
    @PostMapping("logIn")
    public Response logInUser(@RequestBody LogInRequest logInRequest) throws BadCredentialsException {
        return userServiceImpl.logInUser(logInRequest);
    }
}
