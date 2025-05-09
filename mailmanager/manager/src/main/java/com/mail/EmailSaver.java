
package com.mail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.UUID;

public class EmailSaver {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/jamesdb"; // ou autre nom si différent
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "1234";

    public static void saveEmailSent(
        int userId,
        String sender,
        String recipient,
        String subject,
        String body
    ) {
        String sql = "INSERT INTO messages (user_id, message_id, sender, recipients, subject, body, received_at, read, folder) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId); // e.g. 1
            stmt.setString(2, UUID.randomUUID().toString()); // message_id unique
            stmt.setString(3, sender);
            stmt.setString(4, recipient);
            stmt.setString(5, subject);
            stmt.setString(6, body);
            stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // received_at
            stmt.setBoolean(8, true); // read = true for sent emails
            stmt.setString(9, "SENT");

            stmt.executeUpdate();
            System.out.println("Email sauvegardé dans la table `messages`.");
        } catch (Exception e) {
            System.err.println(" Erreur lors de la sauvegarde de l'email : " + e.getMessage());
        }
    }
}
