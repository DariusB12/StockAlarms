package org.example.stockalarms.exceptions.customExceptions;

public class AlphaVantageException extends Exception{
    public AlphaVantageException() {
        super();
    }

    public AlphaVantageException(String message) {
        super(message);
    }

    public AlphaVantageException(String message, Throwable cause) {
        super(message, cause);
    }
}
