package org.example.stockalarms.validator;

import org.example.stockalarms.exceptions.customExceptions.ValidationException;
import org.example.stockalarms.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserValidator implements IValidator<UserEntity> {
    @Override
    public void validate(UserEntity entity) throws ValidationException {
        String errors = "";
        if(entity == null)
            throw new ValidationException("invalid user");

        //validate email
        Pattern p = Pattern.compile(".+@.+\\..+");
        String email = entity.getEmail();
        if (email == null || !p.matcher(email).matches()) {
            throw new ValidationException("invalid email");
        }

        //firstName, lastName
        String firstName = entity.getFirstName();
        String lastName = entity.getLastName();
        if (firstName == null || firstName.isEmpty()) {
            throw new ValidationException("invalid first name");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new ValidationException("invalid last name name");
        }

        //strong pass
        String password = entity.getPassword();
        if (password == null ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*\\W.*") ||
                !password.matches(".*[0-9].*"))
            throw new ValidationException("""
                    the password should contain at least 1 upperCase letter,
                    1 lower case letter
                    1 number
                    a special character
                    and it should be at least 8 characters in length""");

    }
}
