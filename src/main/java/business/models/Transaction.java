package business.models;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private double amount;
    private TransactionType type;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    private Date timestamp;
    private Account account;

    public Transaction(int transactionId, double amount, TransactionType type, Date timestamp, Account account) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
        this.account = account;
    }

    // Getters and Setters

    // ... other methods as needed
}

