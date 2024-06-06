package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Properties props;

    private DatabaseConnection() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/database.properties")) {
            props = new Properties();
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Error loading database properties", e);
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            String dbUrl = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");

            return DriverManager.getConnection(dbUrl, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException("Error establishing database connection", e);
        }
    }
}