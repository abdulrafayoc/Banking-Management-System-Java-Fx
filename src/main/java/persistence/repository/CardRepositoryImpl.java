package persistence.repository;

import business.models.*;

import java.sql.*;
import java.util.List;


public class CardRepositoryImpl implements CrudRepository<Card, String> { // Assuming card number is the ID

    private final String DB_URL;
    private final String USER;
    private final String PASS;

    public CardRepositoryImpl(String dbUrl, String user, String pass) {
        this.DB_URL = dbUrl;
        this.USER = user;
        this.PASS = pass;
    }

    @Override
    public <S extends Card> S save(S card) {
        String sql = "INSERT INTO cards (card_number, cardholder_name, expiry_date, cvv, is_blocked, card_type, account_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, card.getCardNumber());
            stmt.setString(2, card.getCardholderName());
            stmt.setString(3, card.getExpiryDate()); // Assuming expiry date is stored as text
            stmt.setString(4, card.getCvv()); // **Important:** Encrypt CVV before storing
            stmt.setBoolean(5, card.isBlocked());
            stmt.setString(6, card.getCardType().toString());
            // Assuming you have a way to get the account ID the card belongs to
            // stmt.setInt(7, card.getAccount().getAccountId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
        return card;
    }

    @Override
    public Card findById(String s) {
        return null;
    }

    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public void update(Card entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    // ... other CRUD method implementations (findById, findAll, update, deleteById)
}