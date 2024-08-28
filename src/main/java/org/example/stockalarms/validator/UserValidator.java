package org.example.stockalarms.validator;

import org.example.stockalarms.exceptions.ValidationException;
import org.example.stockalarms.model.user.User;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Component
public class UserValidator implements IValidator<User> {
    @Override
    public void validate(User entity) throws ValidationException {
        String errors = "";
        if(entity == null)
            throw new ValidationException("invalid user");

        //validate email
        Pattern p = Pattern.compile(".+@.+\\..+");
        String email = entity.getEmail();
        if (email == null || !p.matcher(email).matches()) {
            errors += "invalid email\n";
        }

        //firstName, lastName
        String firstName = entity.getFirstName();
        String lastName = entity.getLastName();
        if (firstName == null || firstName.isEmpty()) {
            errors += "invalid first name";
        }
        if (lastName == null || lastName.isEmpty()) {
            errors += "invalid first name";
        }

        //strong pass
        String password = entity.getPassword();
        if (password == null ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*\\W.*") ||
                !password.matches(".*[0-9].*"))
            errors += "the password should contain at least 1 upperCase letter,\n " +
                    "1 lower case letter\n" +
                    "1 number\n" +
                    "a special character\n" +
                    "and it should be at least 8 characters in length";
        if (!errors.isEmpty())
            throw new ValidationException(errors);
    }
}
