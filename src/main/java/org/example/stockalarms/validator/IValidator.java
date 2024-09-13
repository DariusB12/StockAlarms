package org.example.stockalarms.validator;

import org.example.stockalarms.exceptions.customExceptions.ValidationException;

public interface IValidator<T> {
    /**
     * Method used to validate the entity
     * @param entity generic
     * @throws ValidationException if the entity is not valid
     */
    void validate(T entity) throws ValidationException;
}
