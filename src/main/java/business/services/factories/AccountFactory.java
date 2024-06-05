package business.services.factories;

import business.models.Account;
import business.models.Branch;
import business.models.Customer;

public interface AccountFactory {
    Account createAccount(double initialBalance, Customer customer, Branch branch);
}