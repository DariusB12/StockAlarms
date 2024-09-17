package org.example.stockalarms.controller;


import lombok.RequiredArgsConstructor;
import org.example.stockalarms.exceptions.customExceptions.AlarmAlreadyDefinedException;
import org.example.stockalarms.exceptions.customExceptions.ValidationException;
import org.example.stockalarms.service.alarm.AlarmService;
import org.example.stockalarms.utils.Response;
import org.example.stockalarms.dto.AlarmDTO;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alarm")
public class AlarmController {
    private final AlarmService alarmService;

    /**
     * Adds the given alarm for the specified stock symbol
     * @param alarm AlarmDTO with inf required for creating an alarm
     * @return Response with success
     */
    @PostMapping
    public Response addAlarm(@RequestBody AlarmDTO alarm) throws ValidationException, AlarmAlreadyDefinedException {
        return alarmService.addAlarm(alarm);
    }

}
