package persistence.repository;

import business.models.User;
import business.models.UserRole;
import persistence.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements CrudRepository<User, Integer> {


    @Override
    public <S extends User> S save(S user) {
        String sql = "INSERT INTO users (phone_number, name, username, password, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection(); // Get connection
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getPhoneNumber());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword()); // **Important:** Hash the password before storing
            stmt.setString(5, user.getRole().toString());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findById(Integer userId) {
        // ... (Implementation for finding a user by ID)
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DatabaseConnection.getInstance().getConnection(); // Get connection
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("user_id");
                String phoneNumber = rs.getString("phone_number");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                UserRole role = UserRole.valueOf(rs.getString("role"));

                User user = new User(id, phoneNumber, name, username, password, role);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (log, rethrow, etc.)
        }

        return users;
    }

    @Override
    public void update(User user) {
        // ... (Implementation for updating a user)
    }

    @Override
    public void deleteById(Integer userId) {
        // ... (Implementation for deleting a user by ID)
    }

    // Custom method to find a user by username (used during authentication)
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Retrieve user data and create a User object
                    int id = rs.getInt("user_id");
                    String phoneNumber = rs.getString("phone_number");
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    UserRole role = UserRole.valueOf(rs.getString("role"));

                    return new User(id, phoneNumber, name, username, password, role);
                }
            }
        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
        return null; // Or throw an exception if the user is not found
    }
}