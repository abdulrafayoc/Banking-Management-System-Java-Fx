package business.services;


import business.models.Account;
import business.models.Customer;
import business.models.Transaction;
import business.models.User;
import persistence.repository.CustomerRepositoryImpl;

import java.util.Date;
import java.util.List;

public class CustomerService {
    private final CustomerRepositoryImpl customerRepository;
    public CustomerService() {
        this.customerRepository = new CustomerRepositoryImpl();
    }
    public CustomerService(CustomerRepositoryImpl customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(String name, String address, Date dateOfBirth, User user) {
        Customer newCustomer = new Customer(generateCustomerId(), name, address, dateOfBirth, user);
        return customerRepository.save(newCustomer);
    }

    public void updatePersonalInfo(Customer customer, String name, String address, String contactNumber) {
        customer.updatePersonalInfo(name, address, contactNumber);
        customerRepository.update(customer);
    }

    public List<Transaction> viewTransactionHistory(Customer customer, Account account) {
        return customer.viewTransactionHistory(account);
    }

    // ... other customer-related methods (findById, etc.)

    private int generateCustomerId() {
        // Logic to generate a unique customer ID
        return 0; // Placeholder
    }
}