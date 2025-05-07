package com.emailapp;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String from, String password, String to, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "25"); // ou 587 si configuré
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false"); // désactivé pour localhost

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("📤 Message envoyé !");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
