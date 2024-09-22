package org.example.stockalarms.controller;


import lombok.RequiredArgsConstructor;
import org.example.stockalarms.dto.AlarmDTO;
import org.example.stockalarms.exceptions.customExceptions.AlarmAlreadyDefinedException;
import org.example.stockalarms.exceptions.customExceptions.AlphaVantageException;
import org.example.stockalarms.exceptions.customExceptions.ValidationException;
import org.example.stockalarms.service.alarm.AlarmService;
import org.example.stockalarms.utils.Response;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alarm")
public class AlarmController {
    private final AlarmService alarmService;

    /**
     * Adds the given alarm for the specified stock symbol
     * @param alarmDTO AlarmDTO data required for creating an alarm (symbol,initialPrice,target and email)
     * @return Response with success http status
     */
    @PostMapping
    public Response addAlarm(@RequestBody AlarmDTO alarmDTO) throws ValidationException, AlarmAlreadyDefinedException, AlphaVantageException {
        return alarmService.addAlarm(alarmDTO);
    }

    /**
     * Retrieves from DB all the alarms of the specified user's email
     * @return Response containing all the alarms of the user
     * @throws ValidationException if the email is null or the user does not exist
     */
    @GetMapping("/{email}")
    public Response getUsersAlarms(@PathVariable String email) throws ValidationException {
        return alarmService.getUsersDefinedAlarms(email);
    }

    /**
     * Deletes the alarm
     * @param id the id of the alarm which will be deleted
     * @return Response with success http status
     * @throws ValidationException if the id is null or if the alarm does not exist
     */
    @DeleteMapping("/{id}")
    public Response deleteAlarm(@PathVariable Integer id) throws ValidationException {
        return alarmService.delete(id);
    }

    /**
     * Updates the alarm target and active status (identified in the db by the id)
     * @param alarmDTO entity with the alarm id,target and active status
     * @return Response with success http status
     * @throws ValidationException if the alarm within the request is invalid
     */
    @PutMapping
    public Response updateAlarm(@RequestBody AlarmDTO alarmDTO) throws ValidationException {
        return alarmService.updateAlarm(alarmDTO);
    }
}
