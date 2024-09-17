package org.example.stockalarms.service.alarm;

import org.example.stockalarms.exceptions.customExceptions.AlarmAlreadyDefinedException;
import org.example.stockalarms.exceptions.customExceptions.ValidationException;
import org.example.stockalarms.utils.Response;
import org.example.stockalarms.dto.AlarmDTO;

public interface AlarmService {

    /**
     * Saves in the DB an Alarm for the specified symbol in the AlarmDTO param
     * @apiNote before saving, the alarm creation updates the information data (variance, check if it should be triggered)
     * @return Response with the created alarm
     * @throws AlarmAlreadyDefinedException if an alarm already exists for that stock symbol
     * @throws ValidationException if the AlarmDTO data is not valid
     */
    Response addAlarm(AlarmDTO alarm) throws AlarmAlreadyDefinedException, ValidationException;
}
