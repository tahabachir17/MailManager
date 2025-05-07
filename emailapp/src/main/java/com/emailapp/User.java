package com.emailapp;

import java.time.LocalDate;

public class User {
    private int id; // utile après insertion
    private String username;
    private String email;
    private String password;
    private String fullName;
    private LocalDate dateDeNaissance;

    public User(String username, String email, String password, String fullName, LocalDate dateDeNaissance) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.dateDeNaissance = dateDeNaissance;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public LocalDate getDateDeNaissance() { return dateDeNaissance; }

    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setDateDeNaissance(LocalDate dateDeNaissance) { this.dateDeNaissance = dateDeNaissance; }
}
