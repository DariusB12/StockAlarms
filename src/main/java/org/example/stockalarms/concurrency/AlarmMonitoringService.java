package org.example.stockalarms.concurrency;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.stockalarms.model.Alarm;
import org.example.stockalarms.model.repo.AlarmRepo;
import org.example.stockalarms.utils.alarm.AlarmUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Data
@Builder
@RequiredArgsConstructor
@EnableScheduling
@Service
public class AlarmMonitoringService{
    private final AlarmUtils alarmUtils;
    private final AlarmRepo alarmRepo;
    private final ConcurrencyManager concurrencyManager;

    /**
     * Periodically checks the active alarms from DB and updates their values
     */
    @Scheduled(fixedRateString = "${alarm.polling.interval}", timeUnit = TimeUnit.SECONDS)
    public void monitorAlarms(){
        for(Alarm alarm : alarmRepo.findAllByActiveIsTrue()){
            concurrencyManager.submitTask(()-> alarmRepo.save(alarmUtils.updateAlarmData(alarm)));
//            System.out.println("Alarm: " + alarm + " was updated-------------------------------");
        }
    }

}
