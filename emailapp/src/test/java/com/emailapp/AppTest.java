package com.emailapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/jamesdb";  // Replace with your database URL
        String user = "postgres";  // Replace with your PostgreSQL username
        String password = "1234";  // Replace with your PostgreSQL password

        try {
            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Establish connection
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Successfully connected to PostgreSQL!");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed! Check the credentials.");
            e.printStackTrace();
        }
    }
    
        
}
