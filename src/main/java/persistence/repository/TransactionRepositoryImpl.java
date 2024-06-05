package persistence.repository;

import business.models.*;
import persistence.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class TransactionRepositoryImpl implements CrudRepository<Transaction, Integer> {



    @Override
    public <S extends Transaction> S save(S transaction) {
        String sql = "INSERT INTO transactions (amount, type, timestamp, account_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection(); // Get connection
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, transaction.getAmount());
            stmt.setString(2, transaction.getType().toString());
            stmt.setTimestamp(3, new Timestamp(transaction.getTimestamp().getTime()));
            stmt.setInt(4, transaction.getAccount().getAccountId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating transaction failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transaction.setTransactionId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating transaction failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
        return transaction;
    }

    @Override
    public Transaction findById(Integer integer) {
        return null;
    }

    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public void update(Transaction entity) {

    }

    @Override
    public void deleteById(Integer integer) {

    }

    // ... other CRUD method implementations (findById, findAll, update, deleteById)
}