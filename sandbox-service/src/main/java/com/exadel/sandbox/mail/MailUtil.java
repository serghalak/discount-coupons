package com.exadel.sandbox.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MailUtil {

    private static final String URL_EVENT = "https://exadel-coupons.web.app/deal/";

    private final JavaMailSender emailSender;

    public void sendSimpleMessage(String mailTo) {
        var message = new SimpleMailMessage();
        message.setFrom("exadelteam2021@gmail.com");
        message.setTo(mailTo);
        message.setSubject("subject");
        message.setText("Promokod: Exadel");
        emailSender.send(message);
    }


    @Async
    public void sendFavoriteMessage(String mailTo, String linkId) {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        var message = new SimpleMailMessage();
        message.setFrom("exadelteam2021@gmail.com");
        message.setTo("serghalak@gmail.com");
        message.setSubject("your favorite event is started");
        message.setText("Click on link to see a new exadel event " + URL_EVENT + linkId);
        emailSender.send(message);
    }
}
