package org.example.stockalarms.controller;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.dto.Request;
import org.example.stockalarms.dto.Response;
import org.example.stockalarms.exceptions.UserException.*;
import org.example.stockalarms.exceptions.ValidationException;
import org.example.stockalarms.model.user.User;
import org.example.stockalarms.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/signUp")
public class SignUpController {
    private final UserServiceImpl userServiceImpl;
    @PostMapping
    public Response registerUser(@RequestBody Request request) throws ValidationException, UserAlreadyExistsException {
        return userServiceImpl.registerUser(request.getUser());
    }

    @GetMapping
    public Request getRequestJson(){
        return Request.builder()
                        .user(User.builder()
                                .build())
                .build();
    }
}
