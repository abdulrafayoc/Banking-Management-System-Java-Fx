package business.services;

import business.models.Bank;
import business.models.Branch;

import persistence.repository.*;

public class BankService {
    private final BankRepositoryImpl bankRepository;

    public BankService(BankRepositoryImpl bankRepository) {
        this.bankRepository = bankRepository;
    }

    public Bank createBank(String name, String address) {
        Bank bank = new Bank(name, address);
        return bankRepository.save(bank);
    }

    public void addBranch(Bank bank, Branch branch) {
        bank.addBranch(branch);
        bankRepository.update(bank);
    }

    public void removeBranch(Bank bank, Branch branch) {
        bank.removeBranch(branch);
        bankRepository.update(bank);
    }

    // ... other bank-related methods (findById, etc.)
}