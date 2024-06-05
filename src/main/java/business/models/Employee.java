package business.models;

import java.util.Date;

public class Employee {
    private int employeeId;
    private String address;
    private Date dateOfBirth;
    private Branch branch;
    private User user;

    public Employee(int employeeId, String address, Date dateOfBirth, Branch branch, User user) {
        this.employeeId = employeeId;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.branch = branch;
        this.user = user;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
// Getters and Setters

    // ... other methods as needed (e.g., processTransaction)
}