package com.exadel.sandbox.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MailUtil {

    private final JavaMailSender emailSender;

    public void sendSimpleMessage(String mailTo) {
        var message = new SimpleMailMessage();
        message.setFrom("exadelteam2021@gmail.com");
        message.setTo(mailTo);
        message.setSubject("subject");
        message.setText("Promokod: Exadel");
        emailSender.send(message);
    }
}
