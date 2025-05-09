package com.mail;

import jakarta.mail.Message;

public class AppLocalServer {
    public static void main(String[] args) {
        String from = "taha@india.com";
        String to = "anas@india.com";
        String subject = "Test Subject";
        String body = "This is a test email using hMailServer and Java.";

        // Send Email
        EmailSendeLocalServer.sendEmail(from, to, subject, body);
        System.out.println("✅ Email successfully sent from " + from + " to " + to);

        // Receive Email
        Message[] messages = EmailReceiverLocalServer.receiveEmails("anas@india.com", "1234");

        // Save first message to DB
        if (messages.length > 0) {
            try {
                Message msg = messages[0];
                SavingMailLocalServer.saveEmail(
                        msg.getFrom()[0].toString(),
                        msg.getAllRecipients()[0].toString(),
                        msg.getSubject(),
                        msg.getContent().toString()
                );
                System.out.println("📥 First received email successfully saved to database.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("⚠️ No new emails received.");
        }
    }
}

