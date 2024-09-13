package org.example.stockalarms.service.email;

public interface EmailService {
    /**
     * sending a simple text mail
     */
    public void sendEmail(String to, String subject, String body);
    /**
     * sending a mail with an html body
     */
    public void sendHtmlEmail(String to, String subject, String body);

}
