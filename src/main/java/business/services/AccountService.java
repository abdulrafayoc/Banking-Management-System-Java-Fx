package business.services;

import business.models.*;
import persistence.repository.*;
import business.services.factories.*;

import java.util.Date;
import java.util.List;

public class AccountService {
    private final AccountRepositoryImpl accountRepository;
    private final TransactionRepositoryImpl transactionRepository;
    private final CardRepositoryImpl cardRepository; // Add CardRepositoryImpl

    public AccountService(AccountRepositoryImpl accountRepository,
                          TransactionRepositoryImpl transactionRepository,
                          CardRepositoryImpl cardRepository) { // Inject CardRepositoryImpl
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
    }

    public Account createAccount(AccountType type, double initialBalance, Customer customer, Branch branch) {
        AccountFactory factory = null;

        // Determine the factory to use based on the account type
        switch (type) {
            case CHECKING:
                factory = new CheckingAccountFactory();
                break;
            case SAVINGS:
                factory = new SavingsAccountFactory();
                break;
            // ... add cases for other account types
            default:
                throw new IllegalArgumentException("Invalid account type: " + type);
        }

        // Use the factory to create the account
        Account newAccount = factory.createAccount(initialBalance, customer, branch);
        accountRepository.save(newAccount);
        return newAccount;
    }

    public void deposit(Account account, double amount) {
        account.deposit(amount);
        accountRepository.update(account); // Assuming you have an update method
        Transaction transaction = new Transaction(generateTransactionId(), amount,
                TransactionType.DEPOSIT, new Date(), account);
        transactionRepository.save(transaction);
    }

    public void withdraw(Account account, double amount) {
        account.withdraw(amount);
        accountRepository.update(account);
        Transaction transaction = new Transaction(generateTransactionId(), amount,
                TransactionType.WITHDRAWAL, new Date(), account);
        transactionRepository.save(transaction);
    }

    public void transfer(Account sourceAccount, Account destinationAccount, double amount) {
        sourceAccount.transferTo(destinationAccount, amount);
        accountRepository.update(sourceAccount);
        accountRepository.update(destinationAccount);
        // Create and save transactions for both source and destination
    }

    public void freezeAccount(Account account) {
        account.setStatus(AccountStatus.FROZEN);
        accountRepository.update(account);
    }

    public void addCardToAccount(Account account, Card card) {
        account.addCard(card);
        accountRepository.update(account);
        cardRepository.save(card); // Save the card to the database
    }

    public void blockCard(Account account, String cardNumber) {
        account.blockCard(cardNumber);
        accountRepository.update(account); // Update account (card status changed)
    }

    // ... other account-related methods (findById, etc.)

    private int generateAccountId() {
        // Logic to generate a unique account ID
        return 0; // Placeholder
    }

    private int generateTransactionId() {
        // Logic to generate a unique transaction ID
        return 0; // Placeholder
    }

    //get account by user id
    public Account getAccountByUserId(int userId) {
        return accountRepository.findById(userId);
    }

    //get balance by account id
    public double getBalanceByAccountId(int accountId) {
        return accountRepository.findById(accountId).getBalance();
    }
}