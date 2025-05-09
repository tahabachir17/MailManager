package com.mail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SavingMailLocalServer {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/hmaildb";
    private static final String USER = "postgres";
    private static final String PASS = "1234";

    public static void saveEmail(String from, String to, String subject, String content) {
        String query = "INSERT INTO emails (sender, receiver, subject, content) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, from);
            stmt.setString(2, to);
            stmt.setString(3, subject);
            stmt.setString(4, content);

            stmt.executeUpdate();
            System.out.println("Email saved to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
