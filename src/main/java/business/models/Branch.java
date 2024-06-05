package business.models;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    private int branchId;
    private String location;
    private List<Account> accounts;
    private List<Employee> employees;

    public Branch(int branchId, String location) {
        this.branchId = branchId;
        this.location = location;
        this.accounts = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    // Getters and Setters

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    // Other methods as needed (e.g., findAccountById, findEmployeeById)
}