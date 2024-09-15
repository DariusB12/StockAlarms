package org.example.stockalarms.service.user;

import static org.example.stockalarms.exceptions.customExceptions.UserException.*;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.utils.requests.LogInRequest;
import org.example.stockalarms.utils.requests.RegisterRequest;
import org.example.stockalarms.utils.Response;
import org.example.stockalarms.exceptions.customExceptions.ValidationException;
import org.example.stockalarms.model.UserEntity;
import org.example.stockalarms.model.repo.UserRepo;
import org.example.stockalarms.validator.IValidator;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final IValidator<UserEntity> userValidator;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public Response registerUser(RegisterRequest registerRequest) throws ValidationException, UserAlreadyExistsException {
        if(!registerRequest.getPassword().equals(registerRequest.getConfirmPassword()))
            throw new ValidationException("password doesn't match");

        UserEntity user = UserEntity.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();
        userValidator.validate(user);

        if(userRepo.findByEmail(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity savedUser = userRepo.save(user);
        savedUser.setPassword(null);
        return Response.builder()
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .statusCode(HttpStatus.OK.value())
                .message("user registered with success")
                .build();

    }

    @Override
    public Response logInUser(LogInRequest logInRequest) throws BadCredentialsException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(logInRequest.getEmail(),logInRequest.getPassword()));

        Optional<UserEntity> optFoundUser = userRepo.findByEmail(logInRequest.getEmail());
        if(optFoundUser.isEmpty())
            throw new BadCredentialsException("email or password incorrect");
        UserEntity user = optFoundUser.get();
        user.setPassword(null);
        return Response.builder()
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .statusCode(HttpStatus.OK.value())
                .message("user logged in with success")
                .build();
    }
}
