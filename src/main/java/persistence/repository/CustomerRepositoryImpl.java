package persistence.repository;

import business.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CrudRepository<Customer, Integer> {

    private final String DB_URL;
    private final String USER;
    private final String PASS;

    public CustomerRepositoryImpl(String dbUrl, String user, String pass) {
        this.DB_URL = dbUrl;
        this.USER = user;
        this.PASS = pass;
    }

    @Override
    public <S extends Customer> S save(S customer) {
        String sql = "INSERT INTO customers (name, address, date_of_birth, user_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            // Convert Date to java.sql.Date
            stmt.setDate(3, new java.sql.Date(customer.getDateOfBirth().getTime()));
            stmt.setInt(4, customer.getUser().getUserId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating customer failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setCustomerId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating customer failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public Customer findById(Integer id) {
        // Implement logic to find a customer by their ID
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("customer_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                Date dateOfBirth = rs.getDate("date_of_birth");
                // ... retrieve other fields

                // Assuming you have a method to fetch User by ID
                // User user = userRepository.findById(...);

                // Customer customer = new Customer(id, name, address, dateOfBirth, user);
                // customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (log, rethrow, etc.)
        }

        return customers;
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customers SET name = ?, address = ?, date_of_birth = ?, user_id = ? WHERE customer_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setDate(3, new java.sql.Date(customer.getDateOfBirth().getTime()));
            stmt.setInt(4, customer.getUser().getUserId());
            stmt.setInt(5, customer.getCustomerId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        // Implement logic to delete a customer by their ID
    }
}