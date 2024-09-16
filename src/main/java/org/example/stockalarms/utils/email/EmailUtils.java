package org.example.stockalarms.utils.email;

import lombok.RequiredArgsConstructor;
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
    public String getHtmlStockAlarmPage() {
        return "<html>" +
                "<head>" +
                "<style>" +
                "body {font-family: Arial, sans-serif; margin: 20px;}" +
                "h1 {color: #4CAF50;}" +
                "p {font-size: 16px;}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<h1>Alarm with the alphaVantage price change</h1>" +
                "<p>content1</p>" +
                "<p>content2</p>" +

                "<p>Best regards,<br/>The Team</p>" +
                "</body>" +
                "</html>";
    }
}
