package persistence.repository;

import business.models.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements CrudRepository<Account, Integer> {

    private final String DB_URL;
    private final String USER;
    private final String PASS;

    public AccountRepositoryImpl(String dbUrl, String user, String pass) {
        this.DB_URL = dbUrl;
        this.USER = user;
        this.PASS = pass;
    }

    @Override
    public <S extends Account> S save(S account) {
        String sql = "INSERT INTO accounts (balance, type, status, customer_id, branch_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, account.getBalance());
            stmt.setString(2, account.getType().toString());
            stmt.setString(3, account.getStatus().toString());
            stmt.setInt(4, account.getCustomer().getCustomerId());
            stmt.setInt(5, account.getBranch().getBranchId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating account failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setAccountId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating account failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account findById(Integer accountId) {
        // ... (Implementation for finding an account by ID)
        return null;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("account_id");
                double balance = rs.getDouble("balance");
                // ... retrieve other fields

                // Assuming you have methods to fetch Customer and Branch by ID
                // Customer customer = customerRepository.findById(...);
                // Branch branch = branchRepository.findById(...);

                // Account account = new Account(id, balance, /* ... other fields */);
                // accounts.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (log, rethrow, etc.)
        }

        return accounts;
    }

    @Override
    public void update(Account account) {
        String sql = "UPDATE accounts SET balance = ?, type = ?, status = ?, customer_id = ?, branch_id = ? WHERE account_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, account.getBalance());
            stmt.setString(2, account.getType().toString());
            stmt.setString(3, account.getStatus().toString());
            stmt.setInt(4, account.getCustomer().getCustomerId());
            stmt.setInt(5, account.getBranch().getBranchId());
            stmt.setInt(6, account.getAccountId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer accountId) {
        // ... (Implementation for deleting an account by ID)
    }
}