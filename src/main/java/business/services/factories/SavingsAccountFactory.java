package business.services.factories;

import business.models.Account;
import business.models.AccountType;
import business.models.Branch;
import business.models.Customer;


public class SavingsAccountFactory implements AccountFactory {
    @Override
    public Account createAccount(double initialBalance, Customer customer, Branch branch) {
        // Logic specific to creating a Savings Account
        return new Account(// ... generate ID,
                initialBalance,
                AccountType.SAVINGS,
                // ... other default values for Savings Account,
                customer,
                branch);
    }
}

// ... (Similarly create LoanAccountFactory if needed)