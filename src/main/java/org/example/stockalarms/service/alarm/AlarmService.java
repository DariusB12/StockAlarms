package org.example.stockalarms.service.alarm;

import org.example.stockalarms.exceptions.customExceptions.AlarmAlreadyDefinedException;
import org.example.stockalarms.exceptions.customExceptions.ValidationException;
import org.example.stockalarms.utils.Request;
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

    /**
     * Retrieves from DB all the alarms defined by the specified user email
     */
    Response getUsersDefinedAlarms(String email) throws ValidationException;

    /**
     * Deletes an alarm based on the provided id
     * @return Response with success status code
     * @throws ValidationException id the id is null or if the alarm does not exist
     */
    Response delete(Integer id) throws ValidationException;


    /**
     * Updates the alarm from the DB with the given variance and target
     * @param request Request entity with the alarm information to be updated
     * @return Response with success status code
     * @throws ValidationException if the alarm id, active status or target is invalid
     */
    Response updateAlarm(Request request) throws ValidationException;
}
