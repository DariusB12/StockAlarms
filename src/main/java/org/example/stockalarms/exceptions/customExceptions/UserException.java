package org.example.stockalarms.exceptions.customExceptions;

public class UserException {
    public static class UserAlreadyExistsException extends Exception{
    }
    public static class UserNotLoggedInException extends Exception{

    }
}
