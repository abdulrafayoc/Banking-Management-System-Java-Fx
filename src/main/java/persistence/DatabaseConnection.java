package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static DatabaseConnection instance; // The single instance
    private Connection connection;

    // Private constructor to prevent external instantiation
    private DatabaseConnection() {
        try {
            // Load database properties from a file (or use environment variables)
            Properties props = new Properties();
            try (FileInputStream fis =  new FileInputStream("src/main/resources/database.properties")) {
                props.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error loading database properties", e);
            }

            String dbUrl = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");

            // Establish the connection
            connection = DriverManager.getConnection(dbUrl, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (e.g., log and rethrow)
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    // Public method to get the single instance of the connection
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) { // Thread-safe initialization
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    // Method to get the database connection
    public Connection getConnection() {
        return connection;
    }
}