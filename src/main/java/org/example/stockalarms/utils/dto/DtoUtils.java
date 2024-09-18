package org.example.stockalarms.utils.dto;


import lombok.RequiredArgsConstructor;
import org.example.stockalarms.dto.AlarmDTO;
import org.example.stockalarms.exceptions.customExceptions.ValidationException;
import org.example.stockalarms.model.Alarm;
import org.example.stockalarms.model.UserEntity;
import org.example.stockalarms.model.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * class used to convert entities from DTO to their original type or reverse
 */
@RequiredArgsConstructor
@Service
public class DtoUtils {
    private final UserRepo userRepo;
    public Alarm fromDto(AlarmDTO alarmDTO) throws ValidationException {
        Optional<UserEntity> user =  userRepo.findByEmail(alarmDTO.getEmail());
        if(user.isEmpty())
            throw new ValidationException("user of the alarm does not exist");
        return Alarm.builder()
                .user(user.get())
                .symbol(alarmDTO.getSymbol())
                .initialPrice(alarmDTO.getInitialPrice())
                .variance(alarmDTO.getVariance())
                .target(alarmDTO.getTarget())
                .active(alarmDTO.getActive())
                .build();
    }

    public AlarmDTO toDto(Alarm alarm) {
        return AlarmDTO.builder()
                .id(alarm.getId())
                .symbol(alarm.getSymbol())
                .initialPrice(alarm.getInitialPrice())
                .variance(alarm.getVariance())
                .target(alarm.getTarget())
                .active(alarm.getActive())
                .email(alarm.getUser().getEmail())
                .build();
    }

}
