package persistence.repository;

import business.models.*;
import persistence.DatabaseConnection;

import java.sql.*;
import java.util.List;

public class ReportRepositoryImpl implements CrudRepository<Report, Integer> {



    @Override
    public <S extends Report> S save(S report) {
        String sql = "INSERT INTO reports (content, timestamp, employee_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection(); // Get connection
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, report.getContent());
            stmt.setTimestamp(2, new Timestamp(report.getTimestamp().getTime()));
            stmt.setInt(3, report.getEmployee().getEmployeeId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating report failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    report.setReportId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating report failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
        return report;
    }

    @Override
    public Report findById(Integer integer) {
        return null;
    }

    @Override
    public List<Report> findAll() {
        return null;
    }

    @Override
    public void update(Report entity) {

    }

    @Override
    public void deleteById(Integer integer) {

    }

    // ... other CRUD method implementations (findById, findAll, update, deleteById)
}