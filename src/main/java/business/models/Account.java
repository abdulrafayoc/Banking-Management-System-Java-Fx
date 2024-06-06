package business.models;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private int accountId;
    private double balance;
    private AccountType type;
    private AccountStatus status;
    private Customer customer;
    private Branch branch;
    private List<Transaction> transactions;
    private List<Card> cards;

    public Account(int id, double balance, AccountType type, AccountStatus status,
                   Customer customer, Branch branch) {
        this.accountId = id;
        this.balance = balance;
        this.type = type;
        this.status = status;
        this.customer = customer;
        this.branch = branch;
        this.transactions = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    public Account(double balance, AccountType type, Customer customer, Branch branch) {
        this.balance = balance;
        this.type = type;
        this.customer = customer;
        this.branch = branch;
        this.transactions = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.status = AccountStatus.ACTIVE;
    }

    public Account(double balance, AccountType type, AccountStatus status,
                   Customer customer, Branch branch) {
        this.balance = balance;
        this.type = type;
        this.status = status;
        this.customer = customer;
        this.branch = branch;
        this.transactions = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    // Getters and Setters

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            // Log transaction (You'll likely use TransactionService for this)
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (status == AccountStatus.FROZEN) {
            throw new IllegalStateException("Cannot withdraw from a frozen account.");
        }
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            // Log transaction
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }
    }

    public void transferTo(Account destinationAccount, double amount) {
        if (status == AccountStatus.FROZEN) {
            throw new IllegalStateException("Cannot transfer from a frozen account.");
        }
        this.withdraw(amount);
        destinationAccount.deposit(amount);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void blockCard(String cardNumber) {
        // Assuming you want to block by card number
        for (Card card : cards) {
            if (card.getCardNumber().equals(cardNumber)) {
                card.block();
                // Additional logic for card blocking (e.g., notification, database update)
                return; // Assuming one card can only be blocked once
            }
        }
        // Handle case where card number is not found (throw exception or log)
    }


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Account getAccount() {
        return this;
    }
}

