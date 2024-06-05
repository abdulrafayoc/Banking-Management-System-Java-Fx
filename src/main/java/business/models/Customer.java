package business.models;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
    private int customerId;
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    private Date dateOfBirth;
    private User user;
    private List<Account> accounts;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Customer(int customerId, String name, String address, Date dateOfBirth, User user) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.user = user;
        this.accounts = new ArrayList<>();
    }

    // Getters and Setters

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void updatePersonalInfo(String name, String address, String contactNumber) {
        this.name = name;
        this.address = address;
        // Update contact number (assuming User object holds contact info)
        this.user.setPhoneNumber(contactNumber);
    }

    public List<Transaction> viewTransactionHistory(Account account) {
        // You might want to add logic to filter transactions by date range, etc.
        return account.getTransactions();
    }


}