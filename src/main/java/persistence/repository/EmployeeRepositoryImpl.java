package persistence.repository;

import business.models.*;

import java.sql.*;
import java.util.List;


public class EmployeeRepositoryImpl implements CrudRepository<Employee, Integer> {

    private final String DB_URL;
    private final String USER;
    private final String PASS;

    public EmployeeRepositoryImpl(String dbUrl, String user, String pass) {
        this.DB_URL = dbUrl;
        this.USER = user;
        this.PASS = pass;
    }

    @Override
    public <S extends Employee> S save(S employee) {
        String sql = "INSERT INTO employees (address, date_of_birth, branch_id, user_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, employee.getAddress());
            stmt.setDate(2, new java.sql.Date(employee.getDateOfBirth().getTime()));
            stmt.setInt(3, employee.getBranch().getBranchId());
            stmt.setInt(4, employee.getUser().getUserId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Creating employee failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    employee.setEmployeeId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating employee failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            // Handle exceptions (log, rethrow, etc.)
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee findById(Integer integer) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public void deleteById(Integer integer) {

    }

    // ... other CRUD method implementations (findById, findAll, update, deleteById)
}