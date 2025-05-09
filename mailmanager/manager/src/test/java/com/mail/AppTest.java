package com.mail;

import java.util.List;

public class AppTest {

    public static void main(String[] args) {
        // Config SMTP Gmail
        String smtpHost = "smtp.gmail.com";
        String smtpPort = "587";
        String username = "mrtahabachir04@gmail.com";
        String password = "dkmg vlaq fmmt sstp"; // mot de passe d'application Gmail
        int userId = 1;

        String subject = "Offre exclusive - Découvrez nos nouveautés !";

        String body = "Bonjour,\n\n" +
                "Nous avons le plaisir de vous présenter nos nouveautés de la semaine :\n\n" +
                "🔹 30% de réduction sur tous les produits technologiques\n" +
                "🔹 Livraison gratuite pour toute commande supérieure à 300 MAD\n" +
                "🔹 Offre valable jusqu’au 20 mai 2025\n\n" +
                "N’attendez plus ! Visitez notre site ou contactez-nous pour plus d’informations.\n\n" +
                "Cordialement,\n" +
                "L’équipe de TahaTech";

        String groupEmail = "test@india.com";

        try {
            // 1. Créer le groupe (s’il n’existe pas)
            MailingListManager.createGroup(groupEmail);

            // 2. Ajouter les membres (s’ils ne sont pas déjà là)
            MailingListManager.addMember(groupEmail, "t.bachir@elaraki.ac.ma");
            MailingListManager.addMember(groupEmail, "rengoku.yami16@gmail.com");

            // 3. Initialiser l’expéditeur
            MailSender sender = new MailSender(smtpHost, smtpPort, username, password);
            MailingListSender listSender = new MailingListSender(sender);

            // 4. Récupérer les membres et envoyer
            List<String> members = MailingListManager.getMembers(groupEmail);
            for (String recipient : members) {
                try {
                    sender.sendEmail(username, recipient, subject, body);
                    System.out.println("Email envoyé à : " + recipient);

                    EmailSaver.saveEmailSent(userId, username, recipient, subject, body);
                    System.out.println("Email sauvegardé pour : " + recipient);
                } catch (Exception e) {
                    System.err.println("Échec pour " + recipient + " : " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println(" Erreur générale : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
