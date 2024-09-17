package org.example.stockalarms.utils.email;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.model.Alarm;
import org.example.stockalarms.model.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailUtils {
    /**
     * Creates the html page content for the alphaVantage alarm email
     * @return a String with the html content
     */
    public String getHtmlStockAlarmPage(Alarm alarm, boolean above) {
        return "<html>" +
                "<head>" +
                "<style>" +
                "body {font-family: Arial, sans-serif; margin: 20px;}" +
                "h1 {color: #4CAF50;}" +
                "p {font-size: 16px;}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<h1>Alarm for" + alarm.getSymbol() + " was triggered</h1>" +
                "<p>The price is " + ((above) ? "above" : "below") + " the target you've set!</p>" +
                "<p>Alarm details:</p>" +
                "<p>"+alarm+"</p>" +

                "<p>Best regards,<br/>The Team</p>" +
                "</body>" +
                "</html>";
    }

    public String getStockEmailSubject(String symbol){
        return symbol + " reached its target!";
    }
}
