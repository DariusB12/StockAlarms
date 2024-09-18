package org.example.stockalarms.utils.alarm;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.dto.StockDTO;
import org.example.stockalarms.exceptions.customExceptions.AlphaVantageException;
import org.example.stockalarms.model.Alarm;
import org.example.stockalarms.service.alphaVantage.AlphaVantageService;
import org.example.stockalarms.service.email.EmailServiceImpl;
import org.example.stockalarms.utils.alphaVantage.AlphaVantageUtils;
import org.example.stockalarms.utils.alphaVantage.json.TimeSeriesIntradayResponse;
import org.example.stockalarms.utils.email.EmailUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlarmUtils {
    private final AlphaVantageService alphaVantageService;
    private final AlphaVantageUtils alphaVantageUtils;
    private final EmailServiceImpl emailService;
    private final EmailUtils emailUtils;

    /**
     * Check for the current price of the alarm's stock,
     * updates the variance of it and sends an email if the alarm should be triggered
     * @return the updated alarm
     */
    public Alarm updateAlarmData(Alarm alarm) throws AlphaVantageException {
        TimeSeriesIntradayResponse response = alphaVantageService.getTimeSeriesIntradayResponse(alarm.getSymbol());
        StockDTO stockDTO = alphaVantageUtils.getStockDTO(response);
        //update the alarm variance;
        alarm.setVariance(getVariance(stockDTO.getCurrentPrice(),alarm.getInitialPrice()));

        //check if the alarm should be triggered
        if(alarm.getActive())
            return checkAlarm(alarm);

        return alarm;
    }

    /**
     * Calculates the variance based on the initialPrice and currentPrice
     * Formula: (new_value - old_value) / old_value
     * @return variance
     */
    public Double getVariance(Double currentPrice,Double initialPrice){
        return ((currentPrice-initialPrice)/initialPrice)*100;
    }

    /**
     * Verify if the alarm should be triggered
     * If the alarm is triggered (the target is reached) then an email is sent and the alarm is set on inactive status
     * @return Alarm with updates status
     */
    private Alarm checkAlarm(Alarm alarm){
        Double target = alarm.getTarget();
        Double variance = alarm.getVariance();
        if(target < 0) {
            // negative target = > check if the variance is below it
            if (variance <= target) {
                alarm.setActive(false);
                alarm.setVariance(null);
                emailService.sendHtmlEmail(alarm.getUser().getEmail(),
                        emailUtils.getStockEmailSubject(alarm.getSymbol()),
                            emailUtils.getHtmlStockAlarmPage(alarm,false));
            }
        }
        else{
            // positive target = > check if the variance is over the target
             if(variance >= target){
                 alarm.setActive(false);
                 alarm.setVariance(null);
                 emailService.sendHtmlEmail(alarm.getUser().getEmail(),
                         emailUtils.getStockEmailSubject(alarm.getSymbol()),
                         emailUtils.getHtmlStockAlarmPage(alarm,true));
             }
            }
        return alarm;
    }
}
