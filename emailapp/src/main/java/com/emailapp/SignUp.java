package com.emailapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

public class SignUp {
    private Connection connection;

    public SignUp() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/jamesdb",
                "postgres",
                "mrtaha2004"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    public boolean checkEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean checkAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() >= 12;
    }

    public boolean register(User user) {
        if (!isValidEmail(user.getEmail())) {
            System.out.println("Invalid email.");
            return false;
        }

        if (checkEmailExists(user.getEmail())) {
            System.out.println("Email already exists.");
            return false;
        }

        if (!checkAge(user.getDateDeNaissance())) {
            System.out.println("User must be at least 12 years old.");
            return false;
        }

        String sql = "INSERT INTO users (username, email, password, full_name) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword()); // tu peux remplacer par un hash plus tard
            ps.setString(4, user.getFullName());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
