package org.example.stockalarms.validator;

import org.example.stockalarms.exceptions.customExceptions.ValidationException;
import org.example.stockalarms.dto.AlarmDTO;
import org.springframework.stereotype.Component;

@Component
public class AlarmDTOValidator implements IValidator<AlarmDTO> {
    @Override
    public void validate(AlarmDTO entity) throws ValidationException {
        String errors = "";
        if(entity == null)
            throw new ValidationException("invalid alarm");

        if(entity.getInitialPrice() == null){
            errors += "invalid initial price";
        }
        if(entity.getTarget() == null){
            errors += "invalid target";
        }
        if(entity.getSymbol() == null){
            errors += "invalid symbol";
        }
        if(entity.getEmail() == null){
            errors += "invalid email";
        }

        if (!errors.isEmpty())
            throw new ValidationException(errors);
    }
}
