package persistence.repository;


import business.models.Branch;
import persistence.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BranchRepositoryImpl implements CrudRepository<Branch, Integer> {


    @Override
    public <S extends Branch> S save(S branch) {
        String sql = "INSERT INTO branches (location) VALUES (?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection(); // Get connection
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, branch.getLocation());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating branch failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Retrieve the auto-generated ID and set it in the branch object
                    branch.setBranchId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating branch failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
        return branch;
    }

    @Override
    public Branch findById(Integer id) {
        // Implement logic to find a branch by its ID
        return null;
    }

    @Override
    public List<Branch> findAll() {
        List<Branch> branches = new ArrayList<>();
        String sql = "SELECT * FROM branches";

        try (Connection conn = DatabaseConnection.getInstance().getConnection(); // Get connection
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("branch_id");
                String location = rs.getString("location");

                Branch branch = new Branch(id, location);
                branches.add(branch);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (log, rethrow, etc.)
        }

        return branches;
    }

    @Override
    public void update(Branch branch) {
        String sql = "UPDATE branches SET location = ? WHERE branch_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection(); // Get connection
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, branch.getLocation());
            stmt.setInt(2, branch.getBranchId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        // Implement logic to delete a branch by its ID
    }
}