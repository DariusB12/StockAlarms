package org.example.stockalarms.service.alarm;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.exceptions.customExceptions.AlarmAlreadyDefinedException;
import org.example.stockalarms.exceptions.customExceptions.AlphaVantageException;
import org.example.stockalarms.exceptions.customExceptions.ValidationException;
import org.example.stockalarms.model.Alarm;
import org.example.stockalarms.model.UserEntity;
import org.example.stockalarms.model.repo.AlarmRepo;
import org.example.stockalarms.model.repo.UserRepo;
import org.example.stockalarms.utils.Response;
import org.example.stockalarms.dto.AlarmDTO;
import org.example.stockalarms.utils.alarm.AlarmUtils;
import org.example.stockalarms.utils.dto.DtoUtils;
import org.example.stockalarms.validator.AlarmDTOValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AlarmServiceImpl implements AlarmService{
    private final AlarmRepo alarmRepo;
    private final AlarmDTOValidator alarmDTOValidator;
    private final AlarmUtils alarmUtils;
    private final DtoUtils dtoUtils;
    private final UserRepo userRepo;
    @Override
    public Response addAlarm(AlarmDTO alarm) throws AlarmAlreadyDefinedException, ValidationException, AlphaVantageException {
        alarmDTOValidator.validate(alarm);

        //check so that no alarm is already defined for that stock symbol
        if(alarmRepo.findBySymbol(alarm.getSymbol()).isPresent())
            throw new AlarmAlreadyDefinedException();

        alarm.setActive(true);
        Alarm updatedAlarm = alarmUtils.updateAlarmData(dtoUtils.fromDto(alarm));
        alarmRepo.save(updatedAlarm);

        return Response.builder()
                .alarm(dtoUtils.toDto(updatedAlarm))
                .message("alarm added with success")
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public Response getUsersDefinedAlarms(String email) throws ValidationException {
        if(email == null)
            throw new ValidationException("null email");

        Optional<UserEntity> userEntity =  userRepo.findByEmail(email);
        if(userEntity.isEmpty())
            throw new ValidationException("user does not exist");

        return Response.builder()
                .alarms(alarmRepo.findByUser(userEntity.get()).stream().map(dtoUtils::toDto).toList())
                .message("user's alarms fetched with success")
                .statusCode(HttpStatus.OK.value())
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .build();
    }

    @Override
    public Response delete(Integer id) throws ValidationException {
        if(id == null)
            throw new ValidationException("null id");
        Optional<Alarm> obj = alarmRepo.findById(id);
        if(obj.isEmpty())
            throw new ValidationException("the alarm does not exist");
        alarmRepo.deleteById(id);

        return Response.builder()
                .message("alarm deleted with success")
                .statusCode(HttpStatus.OK.value())
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .build();
    }

    @Override
    public Response updateAlarm(AlarmDTO alarmDTO) throws ValidationException {
        if(alarmDTO == null || alarmDTO.getId() == null || alarmDTO.getActive() == null || alarmDTO.getTarget() == null)
            throw new ValidationException("the alarm, id, active status or target cannot be null when updating the alarm");
        Optional<Alarm> obj = alarmRepo.findById(alarmDTO.getId());
        if(obj.isEmpty())
            throw new ValidationException("the alarm does not exist in DB");

        Alarm alarm = obj.get();
        alarm.setActive(alarmDTO.getActive());
        alarm.setTarget(alarmDTO.getTarget());
        alarmRepo.save(alarm);

        return Response.builder()
                .message("alarm updated with success")
                .statusCode(HttpStatus.OK.value())
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .build();
    }

}
