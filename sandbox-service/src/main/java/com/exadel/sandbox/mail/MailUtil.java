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

    private static final String MESSAGE = "Dear %s,\n" +
            "here is the promo code you ordered\n\n" +
            "EXDLPROMO21\n\n" +
            "Make sure to visit %s and use it before %s.\n\n" +
            "Sincerely yours, Exadel Coupons Team.\n\n\n" +
            "© Exadel Inc. All rights reserved. All trademarks are property of their respective owners in the US and other countries.";

    private static final String MESSAGE_FOR_SUBSCRIBER = "Dear %s,\n" +
            "You are subscribed for our actions \n" +
            "click on the next link to visit this event %s\n\n" +
            "Sincerely yours, Exadel Team.\n\n\n" +
            "© Exadel Inc. All rights reserved. All trademarks are property of their respective owners in the US and other countries.";


    private final JavaMailSender emailSender;

    @Async
    public void sendSimpleMessage(String mailTo, String userName, String vendorName, String endDate) {
        var message = new SimpleMailMessage();
        message.setFrom("exadelteam2021@gmail.com");
        message.setTo(mailTo);
        message.setSubject("Promokod");
        message.setText(setParameterToMailText(userName, vendorName, endDate));
        emailSender.send(message);
    }


    @Async
    public void sendFavoriteMessage(String mailTo, String linkId, String userName) {

        var message = new SimpleMailMessage();
        message.setFrom("exadelteam2021@gmail.com");
        message.setTo("serghalak@gmail.com");
        message.setSubject("your favorite event is started");
        message.setText(setParameterToMailSubscriptionText(userName, linkId));
        emailSender.send(message);
    }

    private String setParameterToMailText(String userName, String vendorName, String endDate) {
        return String.format(MESSAGE, userName, vendorName, endDate);
    }

    private String setParameterToMailSubscriptionText(String userName, String linkId) {
        return String.format(MESSAGE_FOR_SUBSCRIBER, userName,URL_EVENT + linkId);
    }
}
