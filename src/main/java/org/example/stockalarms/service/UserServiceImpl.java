package org.example.stockalarms.service;

import static org.example.stockalarms.exceptions.UserException.*;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.dto.Response;
import org.example.stockalarms.exceptions.ValidationException;
import org.example.stockalarms.model.user.User;
import org.example.stockalarms.model.user.UserRepo;
import org.example.stockalarms.validator.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;
    public Response registerUser(User user) throws ValidationException, UserAlreadyExistsException {
        userValidator.validate(user);

        if(userRepo.findUserByEmail(user.getEmail()) !=null){
            throw new UserAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);

        return Response.builder()
                .user(savedUser)
                .dateTime(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .message("user registered with success")
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .build();

    }
}
