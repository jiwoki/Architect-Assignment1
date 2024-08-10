package edu.sdccd.cisc191.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection is a utility class that provides a method for establishing a connection
 * to a MySQL database. It uses JDBC (Java Database Connectivity) to connect to the database.
 */
public class DatabaseConnection {

    // JDBC URL for the database, including the database name
    private static final String URL = "jdbc:mysql://localhost:3306/todolist";

    // Username for accessing the database
    private static final String USER = "todo_user";

    // Password for accessing the database
    private static final String PASSWORD = "password";

    /**
     * Establishes and returns a connection to the MySQL database.
     *
     * @return A Connection object to interact with the database.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        // Use DriverManager to establish a connection to the database
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
