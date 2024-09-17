package org.example.stockalarms.service.alarm;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.exceptions.customExceptions.AlarmAlreadyDefinedException;
import org.example.stockalarms.exceptions.customExceptions.ValidationException;
import org.example.stockalarms.model.Alarm;
import org.example.stockalarms.model.repo.AlarmRepo;
import org.example.stockalarms.service.alphaVantage.AlphaVantageService;
import org.example.stockalarms.utils.Response;
import org.example.stockalarms.dto.AlarmDTO;
import org.example.stockalarms.utils.alarm.AlarmUtils;
import org.example.stockalarms.utils.dto.DtoUtils;
import org.example.stockalarms.validator.AlarmValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlarmServiceImpl implements AlarmService{
    private final AlarmRepo alarmRepo;
    private final AlarmValidator alarmValidator;
    private final AlarmUtils alarmUtils;
    private final DtoUtils dtoUtils;
    @Override
    public Response addAlarm(AlarmDTO alarm) throws AlarmAlreadyDefinedException, ValidationException {
        alarmValidator.validate(alarm);

        //check so that no alarm is already defined for that stock symbol
        if(alarmRepo.findBySymbol(alarm.getSymbol()).isPresent())
            throw new AlarmAlreadyDefinedException();

        alarm.setActive(true);
        Alarm updatedAlarm = alarmUtils.updateAlarmData(dtoUtils.fromDto(alarm));
        alarmRepo.save(updatedAlarm);

        return Response.builder()
                .alarmDTO(dtoUtils.toDto(updatedAlarm))
                .message("alarm added with success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

}
