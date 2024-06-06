package business.services;

import business.models.Account;
import business.models.Transaction;
import business.models.TransactionType;

import persistence.repository.TransactionRepositoryImpl;


import java.util.Date;

public class TransactionService {
    private final TransactionRepositoryImpl transactionRepository;
    public TransactionService() {
        this.transactionRepository = new TransactionRepositoryImpl();
    }
    public TransactionService(TransactionRepositoryImpl transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(Account account, double amount, TransactionType type) {
        Transaction newTransaction = new Transaction(generateTransactionId(), amount, type, new Date(), account);
        return transactionRepository.save(newTransaction);
    }

    // ... other transaction-related methods (findById, findByAccount, etc.)

    private int generateTransactionId() {
        // Logic to generate a unique transaction ID
        return 0; // Placeholder
    }
}