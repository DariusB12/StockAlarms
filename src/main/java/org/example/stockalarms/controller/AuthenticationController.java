package org.example.stockalarms.controller;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.utils.requests.LogInRequest;
import org.example.stockalarms.utils.requests.RegisterRequest;
import org.example.stockalarms.utils.Response;
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

    /**
     * Registers a new user in the app
     * @param registerRequest RegisterRequest object with the user data to be persisted
     * @return Response object with the newly registered user
     * @throws ValidationException if the user data is not valid
     * @throws UserAlreadyExistsException if a user already exists in DB with the provided email
     */
    @PostMapping("signUp")
    public Response registerUser(@RequestBody RegisterRequest registerRequest) throws ValidationException, UserAlreadyExistsException {
        return userServiceImpl.registerUser(registerRequest);
    }

    /**
     * Logs in an already registered user
     * @param logInRequest LogInRequest with the credentials needed for logIn
     * @return Response object with the loggedIn user
     * @throws BadCredentialsException if the user's credentials are bad
     */
    @PostMapping("logIn")
    public Response logInUser(@RequestBody LogInRequest logInRequest) throws BadCredentialsException {
        return userServiceImpl.logInUser(logInRequest);
    }
}
