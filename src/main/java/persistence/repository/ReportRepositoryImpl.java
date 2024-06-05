package persistence.repository;

import business.models.*;

import java.sql.*;
import java.util.List;

public class ReportRepositoryImpl implements CrudRepository<Report, Integer> {

    private final String DB_URL;
    private final String USER;
    private final String PASS;

    public ReportRepositoryImpl(String dbUrl, String user, String pass) {
        this.DB_URL = dbUrl;
        this.USER = user;
        this.PASS = pass;
    }

    @Override
    public <S extends Report> S save(S report) {
        String sql = "INSERT INTO reports (content, timestamp, employee_id) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
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