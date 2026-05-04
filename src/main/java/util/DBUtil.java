package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for database connection management.
 * Provides a method to get a connection to the MySQL database.
 */
public class DBUtil {
    // Database connection constants
    private static final String URL = "jdbc:mysql://localhost:3306/gym_system";
    private static final String USER = "root";
    private static final String PASSWORD = "parbati123";

    /**
     * Gets a connection to the database.
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
    }
}
