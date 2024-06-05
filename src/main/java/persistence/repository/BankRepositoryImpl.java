package persistence.repository;


import business.models.Bank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImpl implements CrudRepository<Bank, Integer> {

    private final String DB_URL;
    private final String USER;
    private final String PASS;

    public BankRepositoryImpl(String dbUrl, String user, String pass) {
        this.DB_URL = dbUrl;
        this.USER = user;
        this.PASS = pass;
    }

    @Override
    public <S extends Bank> S save(S bank) {
        String sql = "INSERT INTO banks (name, address) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, bank.getName());
            stmt.setString(2, bank.getAddress());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating bank failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Retrieve the auto-generated ID and set it in the bank object
                    bank.setName(generatedKeys.getString(1));
                } else {
                    throw new SQLException("Creating bank failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
        return bank;
    }

    @Override
    public Bank findById(Integer id) {
        // Implement logic to find a bank by its ID
        return null;
    }

    @Override
    public List<Bank> findAll() {
        List<Bank> banks = new ArrayList<>();
        String sql = "SELECT * FROM banks";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Retrieve by column name
                String name = rs.getString("name");
                String address = rs.getString("address");

                Bank bank = new Bank(name, address);
                banks.add(bank);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (log, rethrow, etc.)
        }

        return banks;
    }

    @Override
    public void update(Bank bank) {
        String sql = "UPDATE banks SET address = ? WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bank.getAddress());
            stmt.setString(2, bank.getName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        // Implement logic to delete a bank by its ID
    }
}