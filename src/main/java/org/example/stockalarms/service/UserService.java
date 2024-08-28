package org.example.stockalarms.service;

import org.example.stockalarms.dto.Response;
import org.example.stockalarms.exceptions.UserException;
import org.example.stockalarms.exceptions.ValidationException;
import org.example.stockalarms.model.user.User;

public interface UserService {
    /**
     * Register the given user
     * @param user the user to be registered
     * @return Response with the user registered
     * @throws ValidationException if the user is null or its credentials are not valid
     * @throws UserException.UserAlreadyExistsException if the user email was found in the DB
     */
    Response registerUser(User user) throws ValidationException, UserException.UserAlreadyExistsException;
}
