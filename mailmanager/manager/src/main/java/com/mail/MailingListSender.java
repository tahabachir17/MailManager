package com.mail;

import java.util.List;

public class MailingListSender {
    private final MailSender mailSender;

    public MailingListSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendToGroup(String from, String groupEmail, String subject, String messageBody) {
        try {
            List<String> recipients = MailingListManager.getMembers(groupEmail);

            if (recipients.isEmpty()) {
                System.out.println(" Aucun membre trouvé dans le groupe : " + groupEmail);
                return;
            }

            for (String email : recipients) {
                try {
                    mailSender.sendEmail(from, email, subject, messageBody);
                    System.out.println("Envoyé à : " + email);
                } catch (Exception e) {
                    System.err.println("Échec pour " + email + " : " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println(" Erreur lors de la récupération des membres de " + groupEmail + " : " + e.getMessage());
        }
    }
}
