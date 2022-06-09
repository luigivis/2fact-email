package com.dilido.twoauth.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;


public class Email {

    public static void sendEmail(String receipt, String uuid) {

        Date date = new Date();
        System.out.println(date);
        String host = "mail.milusystem.com";
        String user = "security.dilido@milusystem.com";
        String password = "79HMk!FMD&zH";
        String to = receipt;
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(user, password);

            }

        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Codigo de Seguridad by Hotel Dilido " + LocalDate.now());
            message.setContent(uuid, "text/html");
            Transport.send(message);
            System.out.println("message sent successfully...");
        } catch (MessagingException var10) {
            var10.printStackTrace();
        }

    }

}
