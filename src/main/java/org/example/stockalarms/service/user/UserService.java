package org.example.stockalarms.service.user;

import org.example.stockalarms.utils.requests.LogInRequest;
import org.example.stockalarms.utils.requests.RegisterRequest;
import org.example.stockalarms.utils.Response;
import org.example.stockalarms.exceptions.customExceptions.UserException;
import org.example.stockalarms.exceptions.customExceptions.ValidationException;
import org.springframework.security.authentication.BadCredentialsException;

public interface UserService {
    /**
     * Register the given user
     * @param registerRequest req info for registering a user
     * @return Response with the user registered
     * @throws ValidationException if the user is null or its credentials are not valid
     * @throws UserException.UserAlreadyExistsException if the user email was found in the DB
     */
    Response registerUser(RegisterRequest registerRequest) throws ValidationException, UserException.UserAlreadyExistsException;

    /**
     * Tries to authenticate the user, creates an Authentication obj and puts it in the SecurityContextHolder
     * @param logInRequest req info for logging a user
     * @return Response object with the user logged in
     * @throws BadCredentialsException if the user's credentials are invalid
     */
    Response logInUser(LogInRequest logInRequest) throws BadCredentialsException;
}
