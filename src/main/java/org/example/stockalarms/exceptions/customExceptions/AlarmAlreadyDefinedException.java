package org.example.stockalarms.exceptions.customExceptions;

public class AlarmAlreadyDefinedException extends Exception{
    public AlarmAlreadyDefinedException() {
        super();
    }

    public AlarmAlreadyDefinedException(String message) {
        super(message);
    }

    public AlarmAlreadyDefinedException(String message, Throwable cause) {
        super(message, cause);
    }
}
