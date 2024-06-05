// CheckingAccountFactory.java
package business.services.factories;

import business.models.Account;
import business.models.AccountType;
import business.models.Customer;
import business.models.Branch;

public class CheckingAccountFactory implements AccountFactory {
    @Override
    public Account createAccount(double initialBalance, Customer customer, Branch branch) {
        // Logic specific to creating a Checking Account
        return new Account(initialBalance,
                AccountType.CHECKING,
                customer,
                branch);
    }

}